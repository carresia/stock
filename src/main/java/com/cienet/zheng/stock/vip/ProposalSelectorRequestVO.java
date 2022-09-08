package com.cienet.zheng.stock.vip;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @description:
 * @author: zhengshan1
 * @create: 2021-11-11 11:15
 **/
@Getter
@Setter
@NoArgsConstructor
public class ProposalSelectorRequestVO implements Serializable {

    private static final long serialVersionUID = 6757934478890294921L;

    /**
     * 帖子类型
     */
    private Integer announceType;
    /**
     * 产品圈子
     * 目前只能选择一个圈子
     */
    private String boardId;
    /**
     * 发帖机型id
     */
    private Long deviceTypeId;
    /**
     * 发帖机型 英文名
     */
    private String deviceType;
    /**
     * 处理状态
     */
    private Integer extraStatus;
    /**
     * 创建时间（0：今天，1：昨天，2：近7天，3：近30天）枚举 不限制 ：99
     */
    private Integer createTime;
    /**
     * MIUI版本（开发版 miui_dev，稳定版 miui_release）枚举
     */
    private String MIUIVersions;
    /**
     * 排序字段(hotValue，createTime) 热度按照点赞数likeCnt衡量
     */
    private String sortField;
    /**
     * 是否有日志(不限-1，有日志1，无日志0)-不限制，就是不传递该参数
     */
    private Integer hasLogOption;
    /**
     * 用户会员等级（Lv1,lv2,lv3,lv4,lv5-不限制，就是不传递该参数null
     */
    private String userLevel;
    /**
     * 超过30天未处理（（是）超过30天未处理1，（否）在30天内未处理-1,不限默认是0-不传递该参数）
     */
    private Integer overThirtyUnDealFlag;

}
