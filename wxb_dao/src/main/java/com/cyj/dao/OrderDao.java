package com.cyj.dao;

import com.cyj.entity.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OrderDao {
    int deleteByPrimaryKey(String orderId);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    List<Order> queryAll();

    /**
     * 使用引用类型如果不加@param在xml中使用属性直接写属性名 如: <if test="name!=null"></>
     *            如果加上@param在xml中使用对象名.属性名,如:<if test="param.name!=null"></if>
     * @param param
     * @return
     */
    List<Order> queryByParms(@Param("param") Map<String ,Object> param);
}