package com.cienet.zheng.stock.testpackage;

import lombok.Data;

import java.util.List;

/**
 * @description: 用户成长值
 * @author: zhengshan1
 * @create: 2021-02-02 14:11
 **/
@Data
public class GrowthScoreData {
    private Long userId;
    private List<ScoreData> scores;
    // 当前成长值
    private int currentGrowthScore;
    // 当前成长值最大值
    private int fullScore;

    // 计算时间段：2019.07-2020.06
    private String calculationPeriod;
    private long updateTime;
    private String explanation;
}
