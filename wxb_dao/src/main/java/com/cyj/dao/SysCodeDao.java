package com.cyj.dao;

import com.cyj.entity.SysCode;

import java.util.List;

public interface SysCodeDao {
    int deleteByPrimaryKey(String codeId);

    int insert(SysCode record);

    int insertSelective(SysCode record);

    SysCode selectByPrimaryKey(String codeId);

    int updateByPrimaryKeySelective(SysCode record);

    int updateByPrimaryKey(SysCode record);

    List<SysCode> queryByFieldCode(String fieldCode);
}