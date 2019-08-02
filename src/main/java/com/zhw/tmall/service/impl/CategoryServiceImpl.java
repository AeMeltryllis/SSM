package com.zhw.tmall.service.impl;

import com.zhw.tmall.mapper.CategoryMapper;
import com.zhw.tmall.pojo.Category;
import com.zhw.tmall.pojo.CategoryExample;
import com.zhw.tmall.service.CategoryService;
import com.zhw.tmall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl  implements CategoryService {
    @Autowired(required = false)
    CategoryMapper categoryMapper;



    public List<Category> list(){
        CategoryExample example =new CategoryExample();
        example.setOrderByClause("id desc");
        return categoryMapper.selectByExample(example);

    }

    @Override
    public void add(Category category) {
        categoryMapper.insert(category);
    }

    @Override
    public void delete(int id) {
        categoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Category get(int id) {
        return categoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(Category category) {
        categoryMapper.updateByPrimaryKey(category);
    }


}