package com.cienet.zheng.stock.vip;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author duanyouti
 * @date 19-12-6
 */
@AllArgsConstructor
public enum CommunityOperationLogType {

    //---------------------------- announce------------------------------------------
    addAnnounce(CommunityModuleType.announce),        //新增帖子
    updateAnnounce(CommunityModuleType.announce),    //修改帖子
    deleteAnnounce(CommunityModuleType.announce),    //删除帖子
    onlineAnnounce(CommunityModuleType.announce),    //上线帖子
    offlineAnnounce(CommunityModuleType.announce),   //下线帖子
    hideAnnounce(CommunityModuleType.announce),       //屏蔽帖子
    cancelHideAnnounce(CommunityModuleType.announce), //取消屏蔽帖子
    topAnnounce(CommunityModuleType.announce),          //置顶帖子
    cancelTopAnnounce(CommunityModuleType.announce),    //取消置顶帖子
    topInBoard(CommunityModuleType.announce),            //版块下置顶帖子
    unTopInBoard(CommunityModuleType.announce),          //取消版块下置顶帖子
    topInTopic(CommunityModuleType.announce),            //话题下置顶帖子
    unTopInTopic(CommunityModuleType.announce),          //取消话题下置顶帖子
    topInEntity(CommunityModuleType.announce),           //实体下置顶帖子
    unTopInEntity(CommunityModuleType.announce),         //取消实体下置顶帖子
    recommendAnnounce(CommunityModuleType.announce),           //推荐帖子
    cancelRecommendAnnounce(CommunityModuleType.announce),    //取消推荐帖子
    goodAnnounce(CommunityModuleType.announce),           //加精帖子
    goodInBoard(CommunityModuleType.announce),           //版块下加精帖子
    recmdInBoard(CommunityModuleType.announce),           //版块下推荐帖子
    unRecmdInBoard(CommunityModuleType.announce),     // 取消版块下推荐帖子
    cancelGoodAnnounce(CommunityModuleType.announce),    //取消加精帖子
    bindToBoard(CommunityModuleType.announce),          //帖子关联到版块
    bindToTopic(CommunityModuleType.announce),          //帖子关联到话题
    changeHotValue(CommunityModuleType.announce),     //修改帖子热度值
    changeLikeCnt(CommunityModuleType.announce),      //修改帖子赞数
    sendAuditAnnounce(CommunityModuleType.announce),   //送审帖子
    uncheckAnnounce(CommunityModuleType.announce),    //帖子状态改为待审核
    updateExtra(CommunityModuleType.announce),  //更新帖子的盖章状态

    //---------------------------- board------------------------------------------
    addBoard(CommunityModuleType.board),      //添加版块
    updateBoard(CommunityModuleType.board),    //修改版块
    onlineBoard(CommunityModuleType.board),    //上线版块
    offlineBoard(CommunityModuleType.board),    //下线板块

    //---------------------------- topic------------------------------------------
    addTopic(CommunityModuleType.topic),       //新增话题
    updateTopic(CommunityModuleType.topic),    //修改话题
    deleteTopic(CommunityModuleType.topic),    //删除话题
    onlineTopic(CommunityModuleType.topic),     //上线话题
    offlineTopic(CommunityModuleType.topic),    //下线话题
    topTopic(CommunityModuleType.topic),           //置顶话题
    cancelTopTopic(CommunityModuleType.topic),    //取消置顶话题
    hotTopic(CommunityModuleType.topic),          //把话题设为热门
    cancelHotTopic(CommunityModuleType.topic),   //取消把话题设为热门
    recmdInTopic(CommunityModuleType.topic),    //话题下推荐帖子
    unRecmdInTopic(CommunityModuleType.topic),   //取消话题下推荐帖子

    //---------------------------- user------------------------------------------
    addStaff(CommunityModuleType.user),       //增加用户权限
    updateStaff(CommunityModuleType.user),    //更新用户权限
    deleteStaff(CommunityModuleType.user),    //删除用户权限
    banUser(CommunityModuleType.user),        //封禁用户
    unBanUser(CommunityModuleType.user),      //取消封禁用户

    //---------------------------- comment------------------------------------------
    offlineComment(CommunityModuleType.comment),    //下线评论

    //---------------------------- entity------------------------------------------

    //---------------------------- unknown------------------------------------------
    unknown(CommunityModuleType.unknown);    //下线评论

    private static final Map<String, CommunityOperationLogType> map = new HashMap<>();

    static {
        for (CommunityOperationLogType type : CommunityOperationLogType.values()) {
            map.put(type.name(), type);
        }
    }

    @Getter
    private CommunityModuleType moduleType;

    public static CommunityOperationLogType getByType(String type) {
        if (type == null || type.isEmpty()) {
            return null;
        }
        return map.get(type);
    }

    public static boolean contains(String type) {
        return getByType(type) != null;
    }
}
