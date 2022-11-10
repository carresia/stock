package com.cienet.zheng.stock.controllers;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.cienet.zheng.stock.dao.DBOperate;
import com.cienet.zheng.stock.dao.StockInfo;
import com.cienet.zheng.stock.dao.UserOperate;
import com.cienet.zheng.stock.models.StockModel;
import com.cienet.zheng.stock.services.StockService;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
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
    void cancelTrade() {
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
        when(service.getAllStockInfo()).thenReturn(stockInfos);

        service.cancelByTradeId(4);
        StockInfo cancelStockInfo = StockInfo.builder()
                .tradeID(4)
                .dBOperator(DBOperate.CANCEL)
                .securityCode("INF")
                .version(2)
                .quantity(0)
                .userOperator(UserOperate.SELL)
                .build();
        stockInfos.add(cancelStockInfo);

        assertEquals(service.getAllStockInfo(), stockInfos);
    }

    /**
     * https://www.javadoop.com/post/java-nio
     * 测试FileChannel
     */
    @Test
    public void testBuffer() throws IOException {
        FileOutputStream outputStream = new FileOutputStream(new File("data.txt"));
        FileChannel fileChannel = outputStream.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put("随机写入一些内容到 Buffer 中".getBytes());
        // Buffer 切换为读模式
        buffer.flip();
        while(buffer.hasRemaining()) {
            // 将 Buffer 中的内容写入文件
            fileChannel.write(buffer);
        }
    }

    /**
     * 测试
     * 1. SocketChannel
     * 2. ServerSocketChannel
     * 之前说 SocketChannel 是 TCP 客户端，这里说的 ServerSocketChannel 就是对应的服务端。
     * @throws IOException
     */
    @Test
    public void testSocketChannel() throws IOException {
        // 打开一个通道
        SocketChannel socketChannel = SocketChannel.open();
        // 发起连接
        socketChannel.connect(new InetSocketAddress("https://www.javadoop.com", 80));
    }

    @Test
    void classifyStatistics() {
        // Setup our mocked service
        List<StockModel> stockModels = new ArrayList<>();
        stockModels.add(new StockModel("REL", 60));
        stockModels.add(new StockModel("ITC", 0));
        stockModels.add(new StockModel("INF", 50));
        when(service.outputSecurityCodeQualityMap()).thenReturn(stockModels);
        service.addTrade("REL1", 20, UserOperate.BUY);
        stockModels.add(new StockModel("REL1", 20));
        assertEquals(service.outputSecurityCodeQualityMap(), stockModels);
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
        when(service.findStockByTradeId(4)).thenReturn(stockInfos);

        StockInfo updateStockInfo = StockInfo.builder()
                .tradeID(4)
                .dBOperator(DBOperate.UPDATE)
                .securityCode("INF")
                .version(2)
                .quantity(20)
                .userOperator(UserOperate.SELL)
                .build();
        stockInfos.add(updateStockInfo);
        service.updateStock(4, "INF", 20, UserOperate.SELL);
        assertEquals(service.findStockByTradeId(4), stockInfos);

        // Execute the GET request
        this.mockMvc.perform(get("/find?trade_id=4"))
                // Validate the response code and content type
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void helloWord() throws Exception {
        this.mockMvc.perform(get("/hello"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("hello word")));
    }
}