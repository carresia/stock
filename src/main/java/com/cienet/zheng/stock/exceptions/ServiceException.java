package com.cienet.zheng.stock.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ServiceException extends RuntimeException {
    private Integer code;
    private String message;
}
