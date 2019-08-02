package com.zhw.tmall.service;
import com.zhw.tmall.pojo.Category;
import com.zhw.tmall.util.Page;

import java.util.List;
public interface CategoryService{

    List<Category> list();
    void add(Category category);

    void delete(int id);
    Category get(int id);
    void update(Category category);
}