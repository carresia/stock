package com.cienet.zheng.stock.dao;


import com.cienet.zheng.stock.dao.module.Food;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FoodDAO {
    int deleteByPrimaryKey(Long id);

    int insert(Food record);

    int insertSelective(Food record);

    Food selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Food record);

    int updateByPrimaryKey(Food record);
}