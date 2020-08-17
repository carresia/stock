package com.cienet.zheng.stock.controllers;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.cienet.zheng.stock.common.ErrCode;


public abstract class BaseController {
    protected String success() {
        return success(null);
    }

    protected String success(Object object) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("message", "success");
        if (object != null) {
            map.put("data", object);
        }
        return toJson(map);
    }

    protected String error(int code) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", code);
        map.put("message", ErrCode.getMessage(code));
        return toJson(map);
    }

    protected String error(int code, String message) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", code);
        map.put("message", message);
        return toJson(map);
    }

    private String toJson(Map<String, Object> res) {
        return JSON.toJSONString(res);
    }
}
