package com.cienet.zheng.stock.biz;

import com.cienet.zheng.stock.dao.FoodDAO;
import com.cienet.zheng.stock.dao.module.Food;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author zhengshan
 * @description
 * @create 2023/2/15
 */
@Service
@Slf4j
public class StockBiz {

    @Resource
    private FoodDAO foodDAO;
    @Transactional(rollbackFor = Throwable.class)
    public void testTransaction() {
        log.info("inset to food");
        Food food = new Food();
        food.setName("rice");
        food.setPrice(2D);
        foodDAO.insert(food);
    }
}
