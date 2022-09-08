package com.cienet.zheng.stock.vip;

import java.util.List;

/**
 * @description:
 * @author: zhengshan1
 * @create: 2021-08-05 14:30
 **/
public class Question {
    private int quesType; // 问题类型：单选0，多选1，填空2
    private String description; // 问题描述
    private List<String> options; // 问题选项
}
