package com.cienet.zheng.stock.dao.module;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class StockInfo {
    private Integer transactionID;
    private Integer tradeID;// mTradeID maybe str
    private Integer version;// mVersion maybe str
    private String securityCode;
    private Integer quantity;
    private DBOperate dBOperator;
    private UserOperate userOperator;
}
