package com.cienet.zheng.stock.mapper;

import com.cienet.zheng.stock.common.StockSelectorExample;
import com.cienet.zheng.stock.dao.StockInfo;
import com.cienet.zheng.stock.dao.DBOperate;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StockInfoMapper {
    void save(StockInfo info);
    List<StockInfo> findBySecurityCode(@Param("security_code") String securityCode);
    List<StockInfo> findByTradeId(@Param("tradle_id") Integer tradeID);
    StockInfo findLastUpdateByTradeId(StockSelectorExample example);
    List<StockInfo> findByDBOperate(@Param("db_operator") DBOperate dbOperator);
    Integer queryMaxTradeId();
    List<StockInfo> findAll();
    List<String> findAllSecurityCode();
}
