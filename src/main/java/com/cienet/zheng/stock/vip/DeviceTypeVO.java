package com.cienet.zheng.stock.vip;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: zhengshan1
 * @create: 2021-11-23 19:36
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceTypeVO {

    private Long deviceTypeId;

    private String deviceTypeName;

    /**
     * 对应 DeviceInfo 的 originName
     */
    private String originName;

}

