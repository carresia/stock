package com.cienet.zheng.stock.mapper;

import java.util.List;

import com.cienet.zheng.stock.dao.StockInfoMapper;
import com.google.common.base.Predicate;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit4.SpringRunner;

import com.cienet.zheng.stock.common.StockSelectorExample;
import com.cienet.zheng.stock.dao.module.DBOperate;
import com.cienet.zheng.stock.dao.module.StockInfo;
import com.cienet.zheng.stock.dao.module.UserOperate;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
//@MybatisTest
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


    String json = "{" +
            "  \"head\":{" +
            "      \"mid\":2475710519," +
            "      \"golds\":0," +
            "      \"availableGolds\":0," +
            "      \"frozenGolds\":0," +
            "      \"exchangeRate\":100" +
            "  }" +
            "}";
    @Test
    void findByTradeID() {
        JsonParser parser = new JsonParser();
        JsonElement appKey = parser.parse(json);
        JsonElement element = optimizeElement(appKey);
        System.out.println(element);
    }

    public JsonElement optimizeElement(JsonElement jsonElement) {
        GsonOptimizer gsonOptimizer = clientTaskOptimizer();
        if (jsonElement == null) {
            return null;
        }
        if (jsonElement.isJsonNull()) {
            return jsonElement;
        }

        if (jsonElement.isJsonPrimitive()) {
            if (gsonOptimizer.valueFilter.apply(jsonElement.getAsJsonPrimitive())) {
                return null;
            }
            return jsonElement;
        } else if (jsonElement.isJsonObject()) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            if (jsonObject.entrySet().isEmpty()) {
                return jsonObject;
            }
            return gsonOptimizer.optimizeObject(jsonObject);
        } else { // jsonElement.isJsonArray()
            JsonArray jsonArray = jsonElement.getAsJsonArray();
            if (jsonArray.size() == 0) {
                return jsonArray;
            }
            return gsonOptimizer.optimizeArray(jsonArray);
        }
    }

    private static GsonOptimizer clientTaskOptimizer() {
        GsonOptimizer.Builder builder = new GsonOptimizer.Builder();
        builder.skipField("sTime").skipField("eTime").skipField("totalNum").skipField("visible").skipField("golds")
                .removeField("serverExtension")
                .doubleFilter(new Predicate<Double>() {
                    @Override
                    public boolean apply(Double input) {
                        return input == 0.0d || input == -1.0d;
                    }
                });
        return builder.build();
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