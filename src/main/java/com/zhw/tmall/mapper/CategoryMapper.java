package com.zhw.tmall.mapper;

import com.zhw.tmall.pojo.Category;
import com.zhw.tmall.util.Page;

import java.util.List;

public interface CategoryMapper {
//    查询
    List<Category> list(Page page);

    public int total();

    void add(Category category);

    void delete(int id);
    Category get(int id);
    void  update(Category category);

}
