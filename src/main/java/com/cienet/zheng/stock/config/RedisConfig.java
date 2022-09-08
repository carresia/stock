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
//    private static String redisCloud = "10.114.64.49:14017,10.142.6.53:31984,10.142.102.52:41838,10.114.64.50:14015,10.136.133.13:31700,10.114.21.55:13285,10.142.6.53:31898,10.136.7.53:14011,10.114.24.35:31480,10.114.24.35:17635,10.142.102.52:41837,10.114.64.49:14012,10.142.74.5:32014,10.142.86.1:41779,10.142.73.12:31985,10.118.6.32:31699,10.142.86.1:41778,10.136.7.53:14007";
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
