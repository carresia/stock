package com.cienet.zheng.stock.biz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhengshan
 * @description
 * @create 2023/2/15
 */
@Service
@Slf4j
public class StockBiz {
    @Transactional
    public void testTransaction() {
    }
}
