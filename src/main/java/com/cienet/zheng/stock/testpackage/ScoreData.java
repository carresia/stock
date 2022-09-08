package com.cienet.zheng.stock.testpackage;

import lombok.Data;

/**
 * @description:
 * @author: zhengshan1
 * @create: 2021-02-02 14:15
 * 表示： 科技拥有力， 新潮消费力，真诚互动力， 热爱活跃力，社群分享力
 **/
@Data
public class ScoreData {
    private int id;
    private String name;
    private int score;
    private int fullScore;
    private String description;
}
