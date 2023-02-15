package com.cienet.zheng.stock.dao.module;

import java.io.Serializable;
import lombok.Data;

/**
 * food
 * @author 
 */
@Data
public class Food implements Serializable {
    /**
     * 数据库自增id
     */
    private Long id;

    /**
     * 食物名称
     */
    private String name;

    /**
     * 食物价格
     */
    private Double price;

    private static final long serialVersionUID = 1L;
}