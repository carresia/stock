package com.cienet.zheng.stock.dao;

import com.cienet.zheng.stock.dao.module.VipUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VipUserDAO {
    int deleteByPrimaryKey(Long id);

    int insert(VipUser record);

    int insertSelective(VipUser record);

    VipUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(VipUser record);

    int updateByPrimaryKey(VipUser record);
}