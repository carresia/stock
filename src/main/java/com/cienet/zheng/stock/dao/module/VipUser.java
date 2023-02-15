package com.cienet.zheng.stock.dao.module;

import java.io.Serializable;
import lombok.Data;

/**
 * vip_user
 * @author 
 */
@Data
public class VipUser implements Serializable {
    /**
     * 数据库自增id
     */
    private Long id;

    /**
     * 用户名字
     */
    private String userName;

    /**
     * 年龄
     */
    private Integer age;

    private static final long serialVersionUID = 1L;
}