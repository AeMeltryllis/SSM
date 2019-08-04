package com.zhw.tmall.service.impl;

import com.zhw.tmall.mapper.PropertyMapper;
import com.zhw.tmall.pojo.Category;
import com.zhw.tmall.pojo.Property;
import com.zhw.tmall.pojo.PropertyExample;
import com.zhw.tmall.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PropertyServiceimpl implements PropertyService {
    @Autowired(required = false)
    PropertyMapper propertyMapper;
    @Override

    public List<com.zhw.tmall.pojo.Property> list(int cid) {
        PropertyExample propertyExample =new PropertyExample();
//        这一行表示查询cid字段
        propertyExample.createCriteria().andCidEqualTo(cid);
        propertyExample.setOrderByClause("id asc");
        return propertyMapper.selectByExample(propertyExample);
    }

    @Override
    public Property get(int id) {
        return propertyMapper.selectByPrimaryKey(id);
    }

    @Override
    public void add(com.zhw.tmall.pojo.Property property) {
        propertyMapper.insert(property);

    }

    @Override
    public void delete(int id) {
        propertyMapper.deleteByPrimaryKey(id);

    }

    @Override
    public void update(com.zhw.tmall.pojo.Property property) {
        propertyMapper.updateByPrimaryKey(property);

    }

}
