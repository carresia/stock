package com.cienet.zheng.stock.services;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhengshan
 * @description
 * @create 2023/6/12
 */
public class AServiceImpl implements AService {
    @Autowired
    private BService bService;
    @Override
    public void testA() {
        bService.testB();
        System.out.println("hello, I'm AService");
    }
}