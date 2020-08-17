package com.cienet.zheng.stock.common;

import java.util.HashMap;
import java.util.Map;

public class ErrCode {
    public final static int OPERATION_FAILURE = 1000;//operation failed
    public final static int PARAM_ERROR = 1001;// Parameter error
    public final static int USERNAME_OR_PASSWORD_FAIL = 1002;//Wrong user name or password
    public final static int NOT_LOGIN = 1003;// Please log in
    public final static int NO_DATA = 1004;
    public final static int NOT_SUFFICIENT_STOCK = 1005;

    public final static Map<Integer, String> messages = new HashMap<Integer, String>();

    static {
        messages.put(OPERATION_FAILURE, "operation failed");
        messages.put(PARAM_ERROR, "Parameter error");
        messages.put(USERNAME_OR_PASSWORD_FAIL, "Wrong user name or password");
        messages.put(NOT_LOGIN, "Please log in");
        messages.put(NO_DATA, "no data");
        messages.put(NOT_SUFFICIENT_STOCK, "There is not enough stock");
    }

    public final static String getMessage(int code) {
        return messages.get(code);
    }
}
