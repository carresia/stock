package com.cienet.zheng.stock.common;

public class Utils {
    public static boolean isNotEmpty(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        return true;
    }
}
