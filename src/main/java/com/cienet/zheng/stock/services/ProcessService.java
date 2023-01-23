package com.cienet.zheng.stock.services;

import com.cienet.zheng.stock.models.UserPointDetail;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhengshan
 * @description
 * @create 2022/12/27
 */
public class ProcessService {

    private final String FORMAT_STR = "yyyy-MM-dd HH:mm:ss";

    public List<UserPointDetail> getUserDetail() throws Exception {
        File filename = new File("/Users/zhengshan/Downloads/1291583193.csv"); // 要读取以上路径的input。txt文件

        InputStreamReader reader = new InputStreamReader(new FileInputStream(filename)); // 建立一个输入流对象reader
        BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言

        List<UserPointDetail> details = new ArrayList<>();
        String line = "";
        line = br.readLine();

        UserPointDetail point = new UserPointDetail();
        String[] lines = line.trim().split(",");
        point.setUserId(lines[0]);
        point.setTimestamp(Long.valueOf(lines[1]));
        point.setPoint(Integer.valueOf(lines[2]));
        point.setReason(lines[3]);
        details.add(point);
        SimpleDateFormat format = new SimpleDateFormat(FORMAT_STR);

        while (line != null) {
            line = br.readLine(); // 一次读入一行数据
            if (line != null && !line.trim().isEmpty()) {
                UserPointDetail tmpPoint = new UserPointDetail();
                String[] linesTmp = line.trim().split(",");
                tmpPoint.setUserId(linesTmp[0]);
                tmpPoint.setTimestamp(Long.valueOf(linesTmp[1]));
                tmpPoint.setPoint(Integer.valueOf(linesTmp[2]));
                tmpPoint.setReason(linesTmp[3]);
                details.add(tmpPoint);
            }
        }
        br.close();
        details = details.stream().sorted(Comparator.comparing(UserPointDetail::getTimestamp)).collect(Collectors.toList());
        String fileName = "/Users/zhengshan/Downloads/1291583193-sort-1.csv";

        StringBuilder sb = new StringBuilder();
        details.stream().forEach(i -> {
            sb.append(i.getUserId()).append(",")
            .append(format.format(i.getTimestamp())).append(",")
            .append(i.getPoint()).append(",")
            .append(i.getReason()).append("\n");
        });
        writeFile(fileName, sb.toString());
        return details;
    }

    public void writeFile(String fileName, String content) throws IOException {
        /* 写入Txt文件 */
        File writename = new File(fileName); // 相对路径，如果没有则要建立一个新的output。txt文件
        writename.createNewFile(); // 创建新文件
        BufferedWriter out = new BufferedWriter(new FileWriter(writename));
        out.write(content); // \r\n即为换行
        out.flush(); // 把缓存区内容压入文件
        out.close(); // 最后记得关闭文件
    }

}
