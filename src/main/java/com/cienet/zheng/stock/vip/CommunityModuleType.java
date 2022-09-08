package com.cienet.zheng.stock.vip;

import java.util.HashMap;
import java.util.Map;

/**
 * @author duanyouti
 * @date 19-12-6
 */
public enum CommunityModuleType {
    user,       //用户
    board,      //圈子
    announce,  //帖子
    comment,   //评论
    topic,     //话题
    entity,    //实体
    unknown;    //未知

    private static final Map<String, CommunityModuleType> map = new HashMap<>();

    static {
        for (CommunityModuleType type : CommunityModuleType.values()) {
            map.put(type.name(), type);
        }
    }

    public static CommunityModuleType getByType(String type) {
        if (type == null || type.isEmpty()) {
            return null;
        }
        return map.get(type);
    }

    public static boolean contains(String type) {
        return getByType(type) != null;
    }

}
