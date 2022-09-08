package com.cienet.zheng.stock.others;

import lombok.extern.slf4j.Slf4j;

/**
 * @description:
 * @author: zhengshan1
 * @create: 2021-05-14 10:23
 **/
@Slf4j
public class ThreadLocalTester {
    private static ThreadLocal<Integer> threadLocal_0 = new ThreadLocal<>();

    public static void test1() {
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                threadLocal_0.set(i);
                Integer value = threadLocal_0.get();
                System.out.println(Thread.currentThread().getName() + ":" + value);
            }
        }, "Thread_have_set").start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                Integer value = threadLocal_0.get();
                System.out.println(Thread.currentThread().getName() + ":" + value);
            }
        }, "Thread_no_set").start();
    }

    public static void test2() {
        new Thread(() -> {
            threadLocal_0.set(520);
            System.out.println("start sleep..." + System.currentTimeMillis());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("end sleep..." + System.currentTimeMillis());
            // gc一次
            System.gc();

            Integer value = threadLocal_0.get();
            System.out.println(Thread.currentThread().getName() + ":" + value);
        }, "Thread_have_set").start();

    }

    public static void main(String[] args) {
        test2();
    }
}
