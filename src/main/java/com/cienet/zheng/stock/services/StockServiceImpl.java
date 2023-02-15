package com.cienet.zheng.stock.services;

import com.cienet.zheng.stock.common.ErrCode;
import com.cienet.zheng.stock.common.StockSelectorExample;
import com.cienet.zheng.stock.dao.module.DBOperate;
import com.cienet.zheng.stock.dao.module.StockInfo;
import com.cienet.zheng.stock.dao.module.UserOperate;
import com.cienet.zheng.stock.exceptions.ServiceException;
import com.cienet.zheng.stock.dao.StockInfoMapper;
import com.cienet.zheng.stock.models.StockModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class StockServiceImpl implements StockService {
    @Autowired
    StockInfoMapper stockInfoRepositroy;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void addTrade(String securityCode, Integer quality, UserOperate userOperate) {
        if (UserOperate.BUY == userOperate) {
            log.debug("start a trade BUY.");
            insertTrade(securityCode, quality, userOperate);
            return;
        }
        log.debug("start a trade SELL.");
        Integer curQuality = getStockQualityBySecurity(securityCode);
        qualityIsAvailable(curQuality, quality);
        insertTrade(securityCode, quality, userOperate);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void cancelByTradeId(Integer tradeId) {
        StockInfo lastUpdateStockInfo = findLastUpdateByTradeId(tradeId);
        stockIsInTrading(lastUpdateStockInfo);
        StockInfo cancelInfo = StockInfo.builder()
                .tradeID(lastUpdateStockInfo.getTradeID())
                .version(lastUpdateStockInfo.getVersion() + 1)
                .securityCode(lastUpdateStockInfo.getSecurityCode())
                .quantity(0)
                .dBOperator(DBOperate.CANCEL)
                .userOperator(UserOperate.SELL).build();
        stockInfoRepositroy.save(cancelInfo);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void updateStock(Integer tradeId, String securityCode, Integer quality, UserOperate userOperate)
            throws ServiceException {
        StockInfo lastUpdateStockInfo = findLastUpdateByTradeId(tradeId);
        securityIsSame(lastUpdateStockInfo.getSecurityCode(), securityCode);
        stockIsInTrading(lastUpdateStockInfo);
        if (UserOperate.BUY == userOperate) {
            log.debug("update a trade BUY.");
            updateTrade(tradeId, lastUpdateStockInfo.getVersion() + 1, securityCode, quality, userOperate);
            return;
        }
        Integer curQuality = getStockQualityBySecurity(securityCode);
        qualityIsAvailable(curQuality, quality);
        updateTrade(tradeId, lastUpdateStockInfo.getVersion() + 1, securityCode, quality, userOperate);
    }

    @Override
    @Transactional
    public List<StockInfo> getAllStockInfo() {
        List<StockInfo> res = stockInfoRepositroy.findAll();
        return Optional.ofNullable(res)
                .orElseThrow(() -> new ServiceException(ErrCode.NO_DATA, ErrCode.getMessage(ErrCode.NO_DATA)));
    }

    @Override
    @Transactional
    public List<StockInfo> findStockByTradeId(Integer tradeId) {
        List<StockInfo> res = stockInfoRepositroy.findByTradeId(tradeId);
        return Optional.ofNullable(res)
                .orElseThrow(() -> new ServiceException(ErrCode.NO_DATA, ErrCode.getMessage(ErrCode.NO_DATA)));
    }

    @Override
    @Transactional
    public List<StockModel> outputSecurityCodeQualityMap() {
        List<String> securityCodes = stockInfoRepositroy.findAllSecurityCode();
        List<StockModel> res = securityCodes.stream()
                .map(e -> new StockModel(e, getStockQualityBySecurity(e))).collect(Collectors.toList());
        return Optional.ofNullable(res)
                .orElseThrow(() -> new ServiceException(ErrCode.NO_DATA, ErrCode.getMessage(ErrCode.NO_DATA)));
    }

    private List<StockInfo> findBySecurityCode(String securityCode) throws ServiceException {
        List<StockInfo> res = stockInfoRepositroy.findBySecurityCode(securityCode);
        return Optional.ofNullable(res)
                .orElseThrow(() -> new ServiceException(ErrCode.PARAM_ERROR, "StockInfo securityCode: " + securityCode + " is not exist."));
    }

    private Integer getStockQualityBySecurity(String securityCode) {
        List<StockInfo> res = findBySecurityCode(securityCode);
        List<Integer> cancelledIds = res.stream()
                .filter(si -> si.getDBOperator() == DBOperate.CANCEL)
                .map(StockInfo::getTradeID).collect(Collectors.toList());
        Map<Integer, List<StockInfo>> map = res.stream()
                .filter(si -> !cancelledIds.contains(si.getTradeID()))
                .collect(Collectors.groupingBy(StockInfo::getTradeID));
        List<StockInfo> tradings = new ArrayList<>();
        map.entrySet().stream().forEach(entry -> {
            StockInfo s = entry.getValue()
                    .stream()
                    .sorted((s1, s2) -> Integer.compare(s2.getVersion(), s1.getVersion()))
                    .collect(Collectors.toList()).get(0);
            tradings.add(s);
        });
        Integer curQuality = tradings.stream()
                .map(si -> si.getUserOperator() == UserOperate.BUY ? si.getQuantity() : -si.getQuantity())
                .reduce(0, Integer::sum);
        return curQuality;
    }

    private void updateTrade(Integer tradeId, Integer version,
                             String securityCode, Integer quality, UserOperate userOperate) {
        StockInfo updateInfo = StockInfo.builder()
                .tradeID(tradeId)
                .version(version)
                .securityCode(securityCode)
                .quantity(quality)
                .dBOperator(DBOperate.UPDATE)
                .userOperator(userOperate).build();
        stockInfoRepositroy.save(updateInfo);
    }

    private void insertTrade(String securityCode, Integer quality, UserOperate userOperate) {
        Integer maxTradeId = stockInfoRepositroy.queryMaxTradeId();
        log.debug("add a new trade, tradeId:{}", maxTradeId + 1);
        StockInfo stockInfo = StockInfo.builder()
                .tradeID(maxTradeId + 1)
                .dBOperator(DBOperate.INSERT)
                .securityCode(securityCode)
                .version(1)
                .quantity(quality)
                .userOperator(userOperate)
                .build();
        stockInfoRepositroy.save(stockInfo);
    }

    private void securityIsSame(String lastSecurityCode, String securityCode) {
        if (lastSecurityCode.equals(securityCode)) {
            throw new ServiceException(ErrCode.PARAM_ERROR, "securityCode is not same.");
        }
    }

    private void qualityIsAvailable(Integer curQuality, Integer sellQuality) {
        if (sellQuality > curQuality) {
            throw new ServiceException(ErrCode.PARAM_ERROR, "Have no enough securityCode: " + sellQuality + " stock.");
        }
    }

    private void stockIsInTrading(StockInfo info) {
        if (info.getDBOperator() == DBOperate.CANCEL) {
            throw new ServiceException(ErrCode.PARAM_ERROR, "StockInfo with tradeId: " + info.getTradeID() + " is end.");
        }
    }

    private StockInfo findLastUpdateByTradeId(Integer tradeId) {
        StockSelectorExample example = new StockSelectorExample(tradeId, "version");
        StockInfo lastUpdateStockInfo = stockInfoRepositroy.findLastUpdateByTradeId(example);
        return Optional.ofNullable(lastUpdateStockInfo)
                .orElseThrow(() -> new ServiceException(ErrCode.PARAM_ERROR, "StockInfo tradeId: " + tradeId + " is not exist."));
    }
}
