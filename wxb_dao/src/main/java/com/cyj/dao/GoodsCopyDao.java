package com.cyj.dao;

import com.cyj.entity.GoodsCopy;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsCopyDao {
    int deleteByPrimaryKey(Integer copyId);

    int insert(GoodsCopy record);

    int insertSelective(GoodsCopy record);

    GoodsCopy selectByPrimaryKey(Integer copyId);

    int updateByPrimaryKeySelective(GoodsCopy record);

    int updateByPrimaryKeyWithBLOBs(GoodsCopy record);

    int updateByPrimaryKey(GoodsCopy record);

    List<GoodsCopy> queryByCustomerId(@Param("customerId") String customerId,@Param("typeId") Integer typeId);

    List<GoodsCopy> queryByCustomerIdPageLike(@Param("search") String search, @Param("customerId") String customerId);
}