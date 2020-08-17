package com.cienet.zheng.stock.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class StockModel {
    private String mSecurityCode;
    private Integer mQuality;
}
