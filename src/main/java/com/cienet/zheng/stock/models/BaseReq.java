package com.cienet.zheng.stock.models;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @description:
 * @author: zhengshan1
 * @create: 2021-09-30 23:39
 **/
@Data
public class BaseReq implements Serializable {
    private static final long serialVersionUID = 2881432521793837808L;
    /**
     * 优惠券的种类
     */
    private long activityCouponId;

    /**
     * 举办的抽奖id
     */
    private long lotteryId;

    /**
     * 店铺马甲号
     */
    private long userId;

    /**
     * 中奖的用户ids
     */
    private List<Long> userIds;
}
