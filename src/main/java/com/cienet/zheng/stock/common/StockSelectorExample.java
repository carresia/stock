package com.cienet.zheng.stock.common;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class StockSelectorExample {
    private Integer tradeId;
    private String orderByClause;
}
