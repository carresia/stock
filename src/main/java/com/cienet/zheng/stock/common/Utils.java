package com.cienet.zheng.stock.common;

import com.google.common.base.Preconditions;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


@Slf4j
public class Utils {

//    @RequestParam(required = false) String userId,
//    @RequestParam(required = false, defaultValue = "") String after,
//    @RequestParam(required = false, defaultValue = "20") @Range(min = 1, max = 100) int limit,
//    @Valid SearchAnnounceCondition condition
    public static void buildMiHomeMiddleUserSearch() throws Exception {
        // 1.读取测试userId
        List<String> userIds = getUserIds();

        StringBuilder stringBuilder = new StringBuilder();
        String appKey = "e60221f56f7c4a4a848a05408fcbc5aa";
        for (int i = 0; i < userIds.size(); i++) {
            String signature = DigestUtils.sha256Hex(userIds.get(i) + appKey);
            stringBuilder.append(userIds.get(i)).append(",")
                    .append(signature).append("\n");
        }
        writeFile("/Users/zhengshan/Downloads/user_announce.txt", stringBuilder.toString());
    }


//    @RequestParam(required = false, defaultValue = "1") @Range(min = 1, max = 1000) int page,
//    @RequestParam(required = false, defaultValue = "20") @Range(min = 1, max = 100) int limit, @Valid SearchAnnounceCondition condition
    public static void buildMiHomeMiddlePageSearch() throws Exception {
        List<String> userIds = getUserIds();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("signature").append(",")
                .append("limit").append(",")
                .append("userId").append(",")
                .append("page").append("\n");

        String appKey = "e60221f56f7c4a4a848a05408fcbc5aa";
        for (int i = 0; i < userIds.size(); i++) {
            int pageNum = getRandom(1, 1000);
            String signature = DigestUtils.sha256Hex(userIds.get(i) + appKey);
            stringBuilder.append(signature).append(",")
                    .append(20).append(",")
                    .append(userIds.get(i)).append(",")
                    .append(pageNum).append("\n");
        }
        writeFile("/Users/zhengshan/Downloads/search_page.csv", stringBuilder.toString());
    }

    /**
     * 从本地文件读取测试userId
     * @return
     */
    private static List<String> getUserIds() throws IOException {
        File filename = new File("/Users/zhengshan/Downloads/userId.txt"); // 要读取以上路径的input。txt文件

        InputStreamReader reader = new InputStreamReader(new FileInputStream(filename)); // 建立一个输入流对象reader
        BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言

        List<String> userIds = new ArrayList<>();
        String line = "";
        line = br.readLine();
        userIds.add(line.trim());
        while (line != null) {
            line = br.readLine(); // 一次读入一行数据
            if (line != null && !line.trim().isEmpty()) {
                userIds.add(line.trim());
            }
        }
        return userIds;
    }


    public static String buildCrowdTestingDetailData() throws Exception {
//        @ReqParam(value = "id", required = true) long id,
        Map<Integer, Integer> ids = new HashMap<>();
        ids.put(1, 6);
        ids.put(2, 7);
        ids.put(3, 8);

        StringBuilder stringBuilder = new StringBuilder();
        int size = 1000;
        for (int i = 0; i < size; i++) {
            int idIndex = getRandom(1, 3);
            stringBuilder.append(ids.get(idIndex))
                    .append("\n");

        }
        return stringBuilder.toString();

    }

    // senderId,senderIcon,receiverId,type=1,title,content,ts,token,signature
//    在调用接口时，需要传入ts、token和signature三个参数：
//    ts是调用接口时的毫秒时间戳
//    signature的生成方式：
//            DigestUtils.sha256Hex(ts + secret)

    public static String buildTopicRecommendFeed() throws Exception {
        File filename = new File("/Users/zhengshan/Downloads/topicId.txt"); // 要读取以上路径的input。txt文件

        InputStreamReader reader = new InputStreamReader(
                new FileInputStream(filename)); // 建立一个输入流对象reader
        BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言

        List<String> topicId = new ArrayList<>();
        String line = "";
        line = br.readLine();
        topicId.add(line.trim());
        while (line != null) {
            line = br.readLine(); // 一次读入一行数据
            if (line != null && !line.trim().isEmpty()) {
                topicId.add(line.trim());
            }
        }
        int size = topicId.size();

        StringBuilder sb = new StringBuilder();
        String appName = "topic_rec";

        List<String> types = new ArrayList<>();
        types.add("rec_new_list");
        types.add("rec_list");
        String recommandType = types.get(getRandom(0, 1));
        int offset = 0;
        while (offset < 1000) {
            int pageNum = offset + 1;
            int index = getRandom(0, size - 1);
            sb.append(topicId.get(index)).append(",")
                    .append(offset).append(",")
                    .append("topic_rec").append(",")
                    .append(recommandType).append(",")
                    .append(pageNum).append("\n");
            offset++;
        }
        return sb.toString();

    }

    public static String getSystemSendData() throws Exception {
        File filename = new File("/Users/zhengshan/Downloads/userId"); // 要读取以上路径的input。txt文件

        InputStreamReader reader = new InputStreamReader(
                new FileInputStream(filename)); // 建立一个输入流对象reader
        BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言

        List<String> userId = new ArrayList<>();
        String line = "";
        line = br.readLine();
        userId.add(line.trim());
        while (line != null) {
            line = br.readLine(); // 一次读入一行数据
            if (line != null && !line.trim().isEmpty()) {
                userId.add(line.trim());
            }
        }
        String senderIcon = "https://cdn.cnbj0.fds.api.mi-img.com/b2c-shopapi-pms/pms_1537323994.03296551.jpg";
        int type = 1;
        String title = "标题title";
        String content = "一天天就知道哈哈哈哈哈";

        String token = "098f6bcd4621d373cade4e832627b4f6";
        String secret = "d9e987e7c1bd6e3c5091270937f50cdb";
        Long ts = System.currentTimeMillis();
        String signature = DigestUtils.sha256Hex(ts + secret);

        StringBuilder stringBuilder = new StringBuilder();
        int size = userId.size();
        for (int i = 0; i < size; i++) {
            int senderId = getRandom(0, size - 1);
            int receiverId = getRandom(0, size - 1);
            stringBuilder.append(userId.get(senderId)).append(",")
                    .append(senderIcon).append(",")
                    .append(userId.get(receiverId)).append(",")
                    .append(type).append(",")
                    .append(title).append(",")
                    .append(content).append(",")
                    .append(ts).append(",")
                    .append(token).append(",")
                    .append(signature).append("\n");

        }

        return stringBuilder.toString();
    }



    public static String getMessageInteract() {
        StringBuilder sb = new StringBuilder();
        int data = 0;
        while (data < 1000) {
            sb.append(getRandom(1, 3)).append(",").append(getRandom(1, 30)).append("\n");
            data++;
        }
        return sb.toString();
    }

    public static String recommandDetail() throws Exception {
        File filename = new File("/Users/zhengshan/Downloads/boardId.txt"); // 要读取以上路径的input。txt文件
        InputStreamReader reader = new InputStreamReader(
                new FileInputStream(filename)); // 建立一个输入流对象reader
        BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言

        StringBuilder stringBuilder = new StringBuilder();
        String line = "";
        line = br.readLine();
        stringBuilder.append(line);
        while (line != null) {
            line = br.readLine(); // 一次读入一行数据
            if (line != null && !line.trim().isEmpty()) {
                stringBuilder.append(getRandom(0, 100)).append(",")
                        .append(line)
                        .append("\n");
            }
        }
        return stringBuilder.toString();
    }

    public static String recommandTab() {
        Map<Integer, String> map = new HashMap<>();
        map.put(0, "product_recommend");
        map.put(1, "product_phone");
        map.put(2, "product_digital");
        StringBuilder sb = new StringBuilder();
        int data = 0;
        while (data < 1000) {
            sb.append(data).append(",").append(map.get(getRandom(0, 2))).append("\n");
            data++;
        }
        return sb.toString();
    }

    public static String followUnFollow() throws IOException {
        File filename = new File("/Users/zhengshan/Downloads/userId"); // 要读取以上路径的input。txt文件
        Map<Integer, Boolean> map = new HashMap<>();
        map.put(0, true);
        map.put(1, false);

        InputStreamReader reader = new InputStreamReader(
                new FileInputStream(filename)); // 建立一个输入流对象reader
        BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言

        StringBuilder stringBuilder = new StringBuilder();
        String line = "";
        line = br.readLine();
        stringBuilder.append(line);
        while (line != null) {
            line = br.readLine(); // 一次读入一行数据
            if (line != null && !line.trim().isEmpty()) {
                stringBuilder.append(line).append(",")
                        .append(map.get(getRandom(0, 1)))
                        .append("\n");
            }
        }
        return stringBuilder.toString();

    }

    public static String dealwithId() throws IOException {
        String res = readFile("/Users/zhengshan/Downloads/id.txt");
        return res;
    }

    // postId=%s&commentId=%s&secCommentId=&subCommentId=&after=&limit=10&isFirstReq=%s
    public static String addComments() throws IOException {
        String pathname = "/Users/zhengshan/Downloads/postId.txt";
        File filename = new File(pathname); // 要读取以上路径的input。txt文件
        InputStreamReader reader = new InputStreamReader(
                new FileInputStream(filename)); // 建立一个输入流对象reader
        BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言

        StringBuilder stringBuilder = new StringBuilder();
        String line = "";
        line = br.readLine();
//        stringBuilder.append(line);
        while (line != null) {
            line = br.readLine(); // 一次读入一行数据
            if (line != null && !line.trim().isEmpty()) {
                stringBuilder.append(line).append(",").append("哈哈哈哈头笑掉了等我捡一下").append("\n");
            }
        }
        return stringBuilder.toString();
    }

    // postId=%s&commentId=%s&secCommentId=&subCommentId=&after=&limit=10&isFirstReq=%s
    public static String addCommentReplay() throws IOException {
        String pathname = "/Users/zhengshan/Downloads/planet2021-02-2414_17_03.txt";
        File filename = new File(pathname); // 要读取以上路径的input。txt文件
        InputStreamReader reader = new InputStreamReader(
                new FileInputStream(filename)); // 建立一个输入流对象reader
        BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言

        StringBuilder stringBuilder = new StringBuilder();
        String line = "";
        line = br.readLine();
//        stringBuilder.append(line);
        while (line != null) {
            line = br.readLine(); // 一次读入一行数据
            if (line != null && !line.trim().isEmpty()) {
                String[] strings = line.trim().split("\t");
                stringBuilder.append(strings[1].trim()).append(",")
                        .append(strings[0].trim()).append(",")
                        .append("嘻嘻").append("\n");
            }
        }
        return stringBuilder.toString();
    }

    // postId=%s&commentId=%s&secCommentId=&subCommentId=&after=&limit=10&isFirstReq=%s
    public static String dealwithIdSecCommentList() throws IOException {
        String pathname = "/Users/zhengshan/Downloads/planet2021-02-2414_17_03.txt";
        File filename = new File(pathname); // 要读取以上路径的input。txt文件
        InputStreamReader reader = new InputStreamReader(
                new FileInputStream(filename)); // 建立一个输入流对象reader
        BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言

        StringBuilder stringBuilder = new StringBuilder();
        String line = "";
        line = br.readLine();
//        stringBuilder.append(line);
        while (line != null) {
            line = br.readLine(); // 一次读入一行数据
            if (line != null && !line.trim().isEmpty()) {
                String[] strings = line.trim().split("\t");
                stringBuilder.append(strings[0].trim()).append(",").append(strings[1].trim()).append(",").append(getRandom(0, 1)).append("\n");
            }
        }
        return stringBuilder.toString();
    }

    // postId=%s&commentId=%s&secCommentId=&subCommentId=&after=&limit=10&isFirstReq=%s
    public static String dealwithIdThumbUp() throws IOException {
        String pathname = "/Users/zhengshan/Downloads/planet2021-02-2414_17_03.txt";
        File filename = new File(pathname); // 要读取以上路径的input。txt文件
        InputStreamReader reader = new InputStreamReader(
                new FileInputStream(filename)); // 建立一个输入流对象reader
        BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言

        StringBuilder stringBuilder = new StringBuilder();
        String line = "";
        line = br.readLine();
//        stringBuilder.append(line);
        while (line != null) {
            line = br.readLine(); // 一次读入一行数据
            if (line != null && !line.trim().isEmpty()) {
                String[] strings = line.trim().split("\t");
                stringBuilder.append(strings[1].trim()).append(",").append(strings[0].trim()).append("\n");
            }
        }
        return stringBuilder.toString();
    }

    public static String createActivityAutoIncreasingData() {
        StringBuilder sb = new StringBuilder();
        int data = 0;
        while (data < 1000) {
            sb.append(data).append(",").append(10).append("\n");
            data++;
        }
        return sb.toString();
    }

    public static String createActivityDetail() {
        StringBuilder sb = new StringBuilder();
        int data = 0;
        while (data < 1000) {
            sb.append(data).append("\n");
            data++;
        }
        return sb.toString();
    }

    public static String createFollowingAnnounceListData() {
        int s = 0;
        int e = 100;
        StringBuilder sb = new StringBuilder();
        int data = 0;
        while (data < 1000) {
            sb.append(getRandom(s + 1, e)).append("\n");
            data++;
        }
        return sb.toString();
    }

    public static String createActivitySignUp() {
        StringBuilder sb = new StringBuilder();
        int data = 0;
        while (data < 1000) {
            sb.append(data).append(",")// 活动id
                    .append("1234567").append(",")//联系方式
                    .append("匹配条件").append(",")//匹配条件
                    .append("想申请").append(",")//申请理由
                    .append("同意协议").append(",")//同意协议
                    .append("true")//问卷
                    .append("\n");
            data++;
        }
        return sb.toString();
    }

    public static String createAutoIncreasingData() {
        int s = 0;
        int e = 1000;
        StringBuilder sb = new StringBuilder();
        int data = 0;
        while (data < 1000) {
            sb.append(getRandom(s, e)).append(",").append(getRandom(s + 1, e)).append("\n");
            data++;
        }
        return sb.toString();
    }

    public static int getRandom(int start, int end) {
        end += 1;
        Random radn = new Random();
        return radn.nextInt(end) % (end - start) + start;
    }

    public static boolean isNotEmpty(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        return true;
    }

    public static List<String> readStringFile(BufferedReader reader) throws IOException {
        Preconditions.checkNotNull(reader);

        List<String> contentList = new ArrayList<>();

        String curLine;
        while ((curLine = reader.readLine()) != null) {
            if (curLine.trim().isEmpty()) {
                continue;
            }
            contentList.add(curLine);
        }

        return contentList;
    }

    /* 读入TXT文件, 绝对路径或相对路径都可以，这里是绝对路径，写入文件时演示相对路径*/
    public static String readFile(String pathname) throws IOException {
        File filename = new File(pathname); // 要读取以上路径的input。txt文件
        InputStreamReader reader = new InputStreamReader(
                new FileInputStream(filename)); // 建立一个输入流对象reader
        BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言

        StringBuilder stringBuilder = new StringBuilder();
        String line = "";
        line = br.readLine();
        stringBuilder.append(line);
        while (line != null) {
            line = br.readLine(); // 一次读入一行数据
            if (line != null && !line.trim().isEmpty()) {
                stringBuilder.append(line).append("\n");
            }
        }
        return stringBuilder.toString();
    }

    public static void writeFile(String fileName, String content) throws IOException {
        /* 写入Txt文件 */
        File writename = new File(fileName); // 相对路径，如果没有则要建立一个新的output。txt文件
        writename.createNewFile(); // 创建新文件
        BufferedWriter out = new BufferedWriter(new FileWriter(writename));
        out.write(content); // \r\n即为换行
        out.flush(); // 把缓存区内容压入文件
        out.close(); // 最后记得关闭文件
    }

    public static <T> T fromJson(String json, Type typeOfT) {
        return new Gson().fromJson(json, typeOfT);
    }

    public static List<Long> readTxtFile(String filepath) {
        List<Long> userIds = new ArrayList<>();
        if (filepath == null) {
            return userIds;
        }
        try (InputStream inputStream = Utils.class.getClassLoader().getResourceAsStream(filepath);
             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    userIds.add(Long.valueOf(line.trim()));
                }
            }
        } catch (Throwable ex) {
            log.error("exception in readFile", ex);
        }
        log.debug("userIds size: {}", userIds.size());
        return userIds;
    }

}
