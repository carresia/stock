package com.cienet.zheng.stock.controllers;

import com.cienet.zheng.stock.dao.StockInfo;
import com.cienet.zheng.stock.dao.UserOperate;
import com.cienet.zheng.stock.models.StockModel;
import com.cienet.zheng.stock.services.StockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@Api("Maintain Equity Positions")
//@RequestMapping("v1")
public class StockController extends BaseController {

    @Autowired
    StockService stockService;

    // first start a trade
    @ApiOperation("Open a trade, Buy or Sell.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "scode", value = "security code.", required = true, type = "String"),
            @ApiImplicitParam(name = "qua", value = "quality, default=0.", required = true, type = "Integer"),
            @ApiImplicitParam(name = "userOperate", value = "user operate buy or sell.", required = true, type = "Enum")
    })
    @GetMapping("add")
    public String addTrade(@RequestParam(name = "scode") String securityCode,
                           @RequestParam(name = "qua", defaultValue = "0") Integer quality,
                           @RequestParam(name = "userOperate") UserOperate userOperate) {
        stockService.addTrade(securityCode, quality, userOperate);
        return success();
    }

    // update
    @ApiOperation("update an existing trade, Buy or Sell. Attention: security code cannot change.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "trade_id", value = "tradeId.", required = true, type = "Integer"),
            @ApiImplicitParam(name = "scode", value = "security code.", required = true, type = "String"),
            @ApiImplicitParam(name = "qua", value = "quality, default=0.", required = true, type = "Integer"),
            @ApiImplicitParam(name = "userOperate", value = "user operate buy or sell.", required = true, type = "Enum")
    })
    @GetMapping("update")
    public String updateTrade(
            @RequestParam(name = "trade_id") Integer tradeId,
            @RequestParam(name = "scode") String securityCode,
            @RequestParam(name = "qua", defaultValue = "0") Integer quality,
            @RequestParam(name = "userOperate") UserOperate userOperate) {
        stockService.updateStock(tradeId, securityCode, quality, userOperate);
        return success();
    }

    @ApiOperation("cancel an existing trade. Attention: security code, quality, user operate would be ignore, just need tradeId.")
    @ApiImplicitParam(name = "trade_id", value = "tradeId.", required = true, type = "Integer")
    @GetMapping("cancel")
    public String cancelTrade(@RequestParam(name = "trade_id") Integer tradeId) {
        stockService.cancelByTradeId(tradeId);
        return success();
    }

    @ApiOperation("statistic current stocks on hand. The output answer.")
    @GetMapping("statistics")
    public String classifyStatistics() {
        List<StockModel> list = stockService.outputSecurityCodeQualityMap();
        return success(list);
    }

    @GetMapping("test")
    public String testTimeout() throws InterruptedException {
        log.info("test timeout...");
        Thread.sleep(1000);
        return success("sleep 1s");
    }

    @GetMapping("test/second")
    public String testTimeout1() throws InterruptedException {
        log.info("test timeout...");
        Thread.sleep(1000);
        return success("sleep 1s");
    }

    @ApiOperation("find stocks information by tradeId.")
    @ApiImplicitParam(name = "trade_id", value = "tradeId.", required = true, type = "Integer")
    @GetMapping(value = "find", produces = MediaType.APPLICATION_JSON_VALUE)
    public String findStockByTradeId(@RequestParam(name = "trade_id") Integer tradeId) {
        List<StockInfo> res = stockService.findStockByTradeId(tradeId);
        return success(res);
    }

    // maybe split pages
    @ApiOperation("find all stocks information in database.")
    @GetMapping("list")
    public String getAllList() {
        List<StockInfo> list = stockService.getAllStockInfo();
        return success(list);
    }

    @GetMapping("hello")
    public String helloWord() {
        return "hello word";
    }

    @GetMapping("search/es")
    public String searchES() {
        return "hello word";
    }
}
