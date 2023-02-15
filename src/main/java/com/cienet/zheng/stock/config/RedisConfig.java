//package com.cienet.zheng.stock.config;
//
//import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
//import redis.clients.jedis.HostAndPort;
//import redis.clients.jedis.JedisCluster;
//
//import java.util.HashSet;
//import java.util.Set;
//
///**
// * @description:
// * @author: zhengshan1
// * @create: 2021-03-08 17:31
// **/
//public class RedisConfig {
//    // 集群地址
//    private static String redisCloud = "";
//    private static String passport = "";// 密码
//
//    public static JedisCluster createJedisCluster() {
//        String[] serverArray = redisCloud.split(",");
//        Set<HostAndPort> nodes = new HashSet<>();
//
//        for (String ipPort : serverArray) {
//            String[] ipPortPair = ipPort.split(":");
//            nodes.add(new HostAndPort(ipPortPair[0].trim(), Integer.valueOf(ipPortPair[1].trim())));
//        }
//
//        // 测试环境下没有密码
//        JedisCluster jedisCluster = new JedisCluster(nodes, 1000, 1000, 1, new GenericObjectPoolConfig());
//        return jedisCluster;
//    }
//
//}
