package com.zhw.tmall.service.impl;

import com.zhw.tmall.mapper.CategoryMapper;
import com.zhw.tmall.pojo.Category;
import com.zhw.tmall.service.CategoryService;
import com.zhw.tmall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl  implements CategoryService {
    @Autowired(required = false)
    CategoryMapper categoryMapper;

    @Override
    public int total() {
       return categoryMapper.total();
    }

    public List<Category> list(Page page){
        return categoryMapper.list(page);
    }

    @Override
    public void add(Category category) {
        categoryMapper.add(category);
    }

    @Override
    public void delete(int id) {
        categoryMapper.delete(id);
    }

    @Override
    public Category get(int id) {
        return categoryMapper.get(id);
    }

    @Override
    public void update(Category category) {
        categoryMapper.update(category);
    }


}