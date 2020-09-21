package com.cyj.dao;

import com.cyj.entity.Member;
import org.apache.ibatis.annotations.Param;

public interface MemberDao {
    int deleteByPrimaryKey(String memeberId);

    int insert(Member record);

    int insertSelective(Member record);

    Member selectByPrimaryKey(String memeberId);

    int updateByPrimaryKeySelective(Member record);

    int updateByPrimaryKey(Member record);

    Member login(@Param("username") String username,@Param("password") String password);
}