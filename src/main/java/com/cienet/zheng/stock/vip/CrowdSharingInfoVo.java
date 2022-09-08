package com.cienet.zheng.stock.vip;

import lombok.Data;

/**
 * @description:
 * @author: zhengshan1
 * @create: 2021-11-05 11:01
 **/
@Data
public class CrowdSharingInfoVo {
    /**
     * 众测id
     */
    private Long crowdTestingId;
    /**
     * 分享类型，目前有两种：0-朋友圈，微信、1-微博
     */
    private Integer sharingType;

    /**
     * 分享标题
     */
    private String title;

    /**
     * 分享文案
     */
    private String copywriting;

    /**
     * 分享图片
     */
    private String image;
}

