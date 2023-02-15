package com.cienet.zheng.stock.biz;

import com.cienet.zheng.stock.dao.FoodDAO;
import com.cienet.zheng.stock.dao.VipUserDAO;
import com.cienet.zheng.stock.dao.module.Food;
import com.cienet.zheng.stock.dao.module.VipUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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
    @Resource
    private VipUserDAO vipUserDAO;
    @Transactional(rollbackFor = Throwable.class)
    public void testTransaction() {
      insert2Food();
      insert2User();
    }

    @Transactional(rollbackFor = Throwable.class)
    public void insert2Food() {
        log.info("inset to food");
        Food food = new Food();
        food.setName("rice");
        food.setPrice(2D);
        foodDAO.insert(food);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
    public void insert2FoodNew() {
        log.info("inset to food");
        Food food = new Food();
        food.setName("tomatto");
        food.setPrice(3D);
        foodDAO.insert(food);
    }

    @Transactional(rollbackFor = Throwable.class)
    public void insert2User() {
        log.info("inset to User");
        VipUser vipUser = new VipUser();
        vipUser.setUserName("zhangsan");
        vipUser.setAge(10);
        vipUserDAO.insert(vipUser);
        int a = 1/0;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
    public void insert2UserNew() {
        log.info("inset to User");
        VipUser vipUser = new VipUser();
        vipUser.setUserName("zhangsan");
        vipUser.setAge(10);
        vipUserDAO.insert(vipUser);
        int a = 1/0;
    }
}
