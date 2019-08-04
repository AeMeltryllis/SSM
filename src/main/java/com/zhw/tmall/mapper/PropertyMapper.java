package com.zhw.tmall.mapper;

import com.zhw.tmall.pojo.Property;
import com.zhw.tmall.pojo.PropertyExample;
import java.util.List;

public interface PropertyMapper {
    int deleteByPrimaryKey(Integer id);
    int insert(Property record);
    
    int insertSelective(Property record);

    List selectByExample(PropertyExample example);

    Property selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Property record);

    int updateByPrimaryKey(Property record);
}