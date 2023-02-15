package com.cienet.zheng.stock;

import com.cienet.zheng.stock.common.Utils;
import com.cienet.zheng.stock.models.BaseReq;
//import com.github.benmanes.caffeine.cache.Caffeine;
//import com.github.benmanes.caffeine.cache.LoadingCache;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Test {

//    private LoadingCache<Long, Optional<String>> activityCache = Caffeine.newBuilder()
//            .initialCapacity(10)
//            .maximumSize(20)
//            // 设置在写入或者更新之后20分钟后，调用 CacheLoader 重新加载
//            .refreshAfterWrite(10, TimeUnit.MINUTES)
//            .expireAfterWrite(25, TimeUnit.MINUTES)
//            .recordStats()
//            .build(this::loadImpl);

//    public static void main2(String[] args) throws Exception {
//        Test test = new Test();
////        String testStr1 = test.activityCache.get(1L).orElse(null);
////        String testStr2 = test.activityCache.get(2L).orElse("sss");
////        System.out.println(testStr1);
////        System.out.println(testStr2);
//        test.test();
//    }
//
//    public static void main1(String[] args) throws Exception {
//        String content = Utils.buildCrowdTestingSignData4special();
//        Utils.writeFile("/Users/zhengshan/Downloads/4-9signdata.txt", content);
//    }

//    public static void mainq(String[] args) throws Exception {
//        // read file
//        InputStream inputStream = Utils.class.getClassLoader().getResourceAsStream("lyz.txt");
//        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//        List<String> content = Utils.readStringFile(reader);
//
//        // 读取lyz.txt 为 userId-announceId文件(创作者&长文/视频)
//        Map<Long, List<String>> idMap = new HashMap<>();
//        content.stream().forEach(s -> {
//            String[] tmp = s.split("\\s+");
//            Long userId = Long.valueOf(tmp[0].trim());
//            List<String> announceIds = Arrays.asList(tmp[1].trim().split(","));
//            idMap.put(userId, announceIds);
//
//        });
//        // 读取good_announceIds.txt 为 加精精华帖
//        InputStream inputStream1 = Utils.class.getClassLoader().getResourceAsStream("good_announceIds.txt");
//        BufferedReader reader1 = new BufferedReader(new InputStreamReader(inputStream1));
//        List<String> goodAnnounces = Utils.readStringFile(reader1);
//
//        // idMap和goodAnnounces取交集
//        Map<Long, List<String>> resMap = new HashMap<>();
//        idMap.entrySet().stream()
//                .forEach(e -> {
//                    List<String> curGoodAnnounces = e.getValue().stream()
//                            .filter(s -> goodAnnounces.contains(s)).collect(Collectors.toList());
//                    if (curGoodAnnounces.size() > 3) {
//                        // 获取帖子
//                        try {
//                            resMap.put(e.getKey(), curGoodAnnounces);
//                        } catch (Throwable throwable) {
//                            //XxlJobLogger.error(logger, "get throwable when searchAnnouncesByAnnounceIds: {}", throwable);
//                        }
//                    }
//                });
//        LinkedHashMap<Long, List<String>> sortedNewMap = resMap.entrySet().stream()
//                .sorted(Collections.reverseOrder(Comparator.comparingInt(e -> e.getValue().size())))
//                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
//                        (e1, e2) -> e1, LinkedHashMap::new));
//        System.out.println(sortedNewMap);
//    }

    public static void main1(String[] args) throws Exception {
        String filePath = "/Users/zhengshan/Downloads/res.txt";
        File file = new File(filePath);
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        List<String> content = Utils.readStringFile(reader);

        StringBuilder finalContent = new StringBuilder();
        content.stream().forEach(s -> {
            int index = s.lastIndexOf(":");
            String tmp = s.substring(index+1);
            finalContent.append("del planet:um:#2:cui:").append(tmp).append("\n");
        });
        String s = finalContent.toString();
        Utils.writeFile("/Users/zhengshan/Downloads/res.cmd", s);

//        Map<Long, List<String>> map = new HashMap<>();
//        content.stream().forEach(s -> {
//            String[] tmp = s.split("\\s+");
//            List<String> announceIds = Arrays.asList(tmp[1].trim().split(","));
//            LinkedHashSet<String> set = new LinkedHashSet<String>(announceIds.size());
//            set.addAll(announceIds);
//            List<String> announceIds1 = new ArrayList<>();
//            announceIds1.addAll(set);
//            map.put(Long.valueOf(tmp[0]), announceIds1);
//        });
//        System.out.println(map);
    }

    public static void test1(String[] args) throws Exception {
        Map<Long, List<String>> map = new HashMap<>();
        List<String> s1 = new ArrayList<>();
        s1.add("1");
        map.put(1L, s1);
        List<String> s2 = new ArrayList<>();
        s2.add("2");
        s2.add("2");
        map.put(2L, s2);

        List<String> s3 = new ArrayList<>();
        s3.add("3");
        s3.add("3");
        s3.add("3");
        map.put(3L, s3);

        LinkedHashMap<Long, List<String>> sortedNewMap = map.entrySet().stream()
                .sorted(Collections.reverseOrder(Comparator.comparingInt(e -> e.getValue().size())))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));

        StringBuilder sb = new StringBuilder();
        sortedNewMap.forEach((k, v) -> {
            sb.append(k).append(v).append("\n");
        });

        StringBuilder sb1 = new StringBuilder();
        map.forEach((k, v) -> {
            sb1.append(k).append(v).append("\n");
        });

        System.out.println(sb1);
    }

    public static Date getNowDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        ParsePosition pos = new ParsePosition(8);
        Date currentTime_2 = formatter.parse(dateString, pos);
        return currentTime_2;
    }


    public static <T> T fromJsonTurning2Hump(String json, Type typeOfT) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        Gson gson = gsonBuilder.create();
        return gson.fromJson(json, typeOfT);
    }

    public void test() throws UnsupportedEncodingException {
        BaseReq req = new BaseReq();
        req.setActivityCouponId(42);
        req.setUserId(1205160724L);
        req.setLotteryId(152);
        List<Long> userIds = new ArrayList<>();
        userIds.add(2475710519L);
        req.setUserIds(userIds);

        Gson gson = new Gson();
        String reqStr = gson.toJson(req);
        String json = URLEncoder.encode(reqStr, "utf-8");
        System.out.println(json);
    }

    private Optional<String> loadImpl(Long userId) {
        if (userId == 1L) {
            return Optional.of("1111");
        }
        return Optional.empty();
    }

//    public static String mishopPost(Map<String, Object> bizParam, String url, String appId, String appKey, String clientId) {
//        Map<String, Object> param = new HashMap<String, Object>() {{
//            put("app_id", appId);
//            put("client_id", clientId);
//            putAll(bizParam);
//        }};
//        RequestBody body = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded"),
//                ApiUtil.formatUrlMapWithKeyAsc(bizParam, true, false) +
//                        "&client_id=" + clientId +
//                        "&app_id=" + appId +
//                        "&sign=" + DigestUtils.md5Hex(formatUrlMapWithKeyAsc(param, true, false) +
//                        "&key=" + appKey));
//        Request request = new Request.Builder()
//                .url(url)
//                .method("POST", body)
//                .addHeader("Content-Type", "application/x-www-form-urlencoded")
//                .build();
//
//        String result = null;
//        try {
//            Response response = new OkHttpClient().newBuilder()
//                    //设置连接超时时间
//                    .connectTimeout(5, TimeUnit.SECONDS)
//                    //设置读取超时时间
//                    .readTimeout(10, TimeUnit.SECONDS)
//                    .build().newCall(request).execute();
//            result = response.body().string();
//        } catch (Exception e) {
//            log.error("mishopPost error {}", e, e);
//        }
//        return result;
//    }

}
