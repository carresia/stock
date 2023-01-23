package com.cienet.zheng.stock.models;

import lombok.Data;

/**
 * @author zhengshan
 * @description
 * @create 2022/12/27
 */
@Data
public class UserPointDetail {
    private String userId;
    private Long timestamp;
    private Integer point;
    private String reason;
}
