package com.cienet.zheng.stock.services;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhengshan
 * @description
 * @create 2023/6/12
 */
public class BServiceImpl implements BService {
    @Autowired
    private AService aService;
    @Override
    public void testB() {
        System.out.println("hello, I'm BService-B");
    }

    @Override
    public void testB1() {
        System.out.println("hello, I'm BService-B1");
        aService.testA();
    }
}
