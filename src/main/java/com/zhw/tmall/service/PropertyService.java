package com.zhw.tmall.service;

import com.zhw.tmall.pojo.Category;
import com.zhw.tmall.pojo.Property;

import java.util.List;

public interface PropertyService {
//    因为在业务上需要查询某个分类下的属性，所以list方法会带上对应分类的id。
    List<Property> list(int cid);
    Property get(int id);
    void add(Property property);

    void delete(int id);

    void update(Property property);

}

