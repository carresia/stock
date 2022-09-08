package com.cienet.zheng.stock.testpackage;

import lombok.Data;

/**
 * @author zhengshan1
 * @Description 从小米商城返回的数据，
 * 其中 Data 对应 planet 的 GrowthSocreData
 */

@Data
public class GrowthScoreResultBody {
    private String code;
    private String msg;
    private String info;
    private String fields;
    private String exeTime;

    private Data data;

    @lombok.Data
    public class Data {
        private long userId;

        // 真诚互动力
        private int interactionScore;
        private int interactionScoreCompareLastMonth;
        private int interactionScoreMax;

        // 以后商城会去掉
        private int baseScore;
        private int baseScoreCompareLastMonth;
        private int baseScoreMax;

        // 新潮消费力
        private int shoppingScore;
        private int shoppingScoreCompareLastMonth;
        private int shoppingScoreMax;

        // 科技拥有力,智能互联
        private int smartInterconnectionScore;
        private int smartInterconnectionScoreCompareLastMonth;
        private int smartInterconnectionScoreMax;

        // 热爱活跃力
        private int activeScore;
        private int activeScoreCompareLastMonth;
        private int activeScoreMax;

        // 社群分享力
        private int rewardScore;
        private int rewardScoreCompareLastMonth;
        private int rewardScoreMax;

        private int score;
        private int scoreCompareLastMonth;

        private String calculationPeriod;
        private double percent;
        private long rank;
        private int isNew;//代表啥？
        // 这个记录在商城表里，不会更新，只会每月新增一次，所以它对应planet的updateTime
        private long createTime;
    }
}