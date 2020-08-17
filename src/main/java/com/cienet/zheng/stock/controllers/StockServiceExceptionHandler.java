package com.cienet.zheng.stock.controllers;

import com.alibaba.fastjson.JSON;
import com.cienet.zheng.stock.common.ErrCode;
import com.cienet.zheng.stock.exceptions.ServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class StockServiceExceptionHandler {

    @ResponseBody
    @ExceptionHandler(RuntimeException.class)
    public String serviceExceptionHandler(HttpServletRequest req, Exception ex) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (ex instanceof ServiceException) {
            result.put("code", ((ServiceException)ex).getCode());
        } else {
            result.put("code", ErrCode.OPERATION_FAILURE);
        }
        result.put("message", ex.getMessage());
        return JSON.toJSONString(result);
    }
}
