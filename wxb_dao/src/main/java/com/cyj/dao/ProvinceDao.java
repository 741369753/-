package com.cyj.dao;

import com.cyj.entity.Province;

import java.util.List;

public interface ProvinceDao {
    int deleteByPrimaryKey(String provinceId);

    int insert(Province record);

    int insertSelective(Province record);

    Province selectByPrimaryKey(String provinceId);

    int updateByPrimaryKeySelective(Province record);

    int updateByPrimaryKey(Province record);

    List<Province> queryAll();
}