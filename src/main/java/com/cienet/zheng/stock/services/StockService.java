package com.cienet.zheng.stock.services;


import com.cienet.zheng.stock.dao.module.StockInfo;
import com.cienet.zheng.stock.dao.module.UserOperate;
import com.cienet.zheng.stock.exceptions.ServiceException;
import com.cienet.zheng.stock.models.StockModel;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface StockService {
    void addTrade(String securityCode, Integer quality, UserOperate userOperate) throws ServiceException, DataAccessException;

    void cancelByTradeId(Integer tradeId) throws ServiceException, DataAccessException;

    void updateStock(Integer tradeId, String securityCode,
                     Integer quality, UserOperate userOperate) throws ServiceException, DataAccessException;

    List<StockInfo> getAllStockInfo() throws ServiceException, DataAccessException;

    List<StockInfo> findStockByTradeId(Integer tradeId) throws ServiceException, DataAccessException;

    List<StockModel> outputSecurityCodeQualityMap() throws ServiceException, DataAccessException;
}
