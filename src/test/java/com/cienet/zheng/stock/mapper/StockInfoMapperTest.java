package com.cienet.zheng.stock.mapper;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit4.SpringRunner;

import com.cienet.zheng.stock.common.StockSelectorExample;
import com.cienet.zheng.stock.dao.DBOperate;
import com.cienet.zheng.stock.dao.StockInfo;
import com.cienet.zheng.stock.dao.UserOperate;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class StockInfoMapperTest {

    @Autowired
    private StockInfoMapper stockInfoMapper;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void save() {
        StockInfo info = StockInfo.builder()
                .tradeID(4)
                .dBOperator(DBOperate.UPDATE)
                .securityCode("INF")
                .version(2)
                .quantity(20)
                .userOperator(UserOperate.BUY)
                .build();
        stockInfoMapper.save(info);
        List<StockInfo> res = stockInfoMapper.findBySecurityCode("INF");
        assertEquals(res.size(), 3);
    }

    @Test
    void findBySecurityCode() {
        List<StockInfo> res = stockInfoMapper.findBySecurityCode("INF");
        assertEquals(res.size(), 2);
    }

    @Test
    void findByTradeId() {
        List<StockInfo> res = stockInfoMapper.findByTradeId(1);
        assertEquals(2, res.size());
        assertEquals(50, res.get(0).getQuantity());
    }

    @Test
    void findLastUpdateByTradeId() {
        StockSelectorExample example = new StockSelectorExample(1, "version");
        StockInfo res = stockInfoMapper.findLastUpdateByTradeId(example);
        assertEquals(2, res.getVersion());
    }

    @Test
    void findByDBOperate() {
        List<StockInfo> res = stockInfoMapper.findByDBOperate(DBOperate.CANCEL);
        assertEquals(1, res.size());
        assertEquals(2, res.get(0).getTradeID());
    }

    @Test
    void queryMaxTradeId() {
        int res = stockInfoMapper.queryMaxTradeId();
        assertEquals(4, res);
    }

    @Test
    void findAll() {
    }

    @Test
    void findAllSecurityCode() {
        List<String> res = stockInfoMapper.findAllSecurityCode();
        assertEquals(3, res.size());
    }
}