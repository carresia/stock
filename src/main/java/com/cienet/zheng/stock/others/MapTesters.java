package com.cienet.zheng.stock.others;

/**
 * @description:
 * @author: zhengshan1
 * @create: 2021-05-14 10:05
 **/
public class MapTesters {
    static final int MAXIMUM_CAPACITY = 1 << 30;

    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    public static void main(String[] args) {
        int a = 7;
        int res = tableSizeFor(a);
        System.out.println(res);
    }
}
