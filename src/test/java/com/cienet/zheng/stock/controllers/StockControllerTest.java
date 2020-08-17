package com.cienet.zheng.stock.controllers;

import com.cienet.zheng.stock.StockApplication;
import com.cienet.zheng.stock.dao.DBOperate;
import com.cienet.zheng.stock.dao.StockInfo;
import com.cienet.zheng.stock.dao.UserOperate;
import com.cienet.zheng.stock.services.StockService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@SpringBootTest
@AutoConfigureMockMvc
@EnableAutoConfiguration
@ContextConfiguration(classes = {StockApplication.class},locations = {"classpath:mappers/StockInfoMapper.xml"})
class StockControllerTest {

    @MockBean
    private StockService service;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addTrade() {
    }

    @Test
    void updateTrade() {
    }

    @Test
    void cancelTrade() {
    }

    @Test
    void classifyStatistics() {
        // Setup our mocked service
//        List<StockModel> stockModels = new ArrayList<>();
//        stockModels.add(new StockModel("REL", 60));
//        stockModels.add(new StockModel("ITC", 0));
//        stockModels.add(new StockModel("INF", 50));
    }

    @Test
    void findStockByTradeId() throws Exception {
        // Setup our mocked service
        List<StockInfo> stockInfos = new ArrayList<>();
        StockInfo stockInfo = StockInfo.builder()
                .tradeID(4)
                .dBOperator(DBOperate.INSERT)
                .securityCode("INF")
                .version(1)
                .quantity(20)
                .userOperator(UserOperate.SELL)
                .build();
        stockInfos.add(stockInfo);
        doReturn(stockInfos).when(service).findStockByTradeId(4);

        // Execute the GET request
        mockMvc.perform(get("/stock/find?trade_id=4"))
                // Validate the response code and content type
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void getAllList() {
    }

    @Test
    void helloWord() throws Exception {
        this.mockMvc.perform(get("/stock/hello"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("hello word")));
    }
}