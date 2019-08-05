package com.zhw.tmall.service.impl;

import com.zhw.tmall.mapper.ProductImageMapper;
import com.zhw.tmall.pojo.Product;
import com.zhw.tmall.pojo.ProductImage;
import com.zhw.tmall.pojo.ProductImageExample;
import com.zhw.tmall.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductImageimpl implements ProductImageService {
   @Autowired(required = false)
    ProductImageMapper productImageMapper;

    @Override
    public void add(ProductImage productImage) {
        productImageMapper.insert(productImage);

    }

    @Override
    public void delete(int id) {
        productImageMapper.deleteByPrimaryKey(id);

    }

    @Override
    public void update(ProductImage productImage) {
        productImageMapper.updateByPrimaryKey(productImage);

    }

    @Override
    public ProductImage get(int id) {
        return null;
    }

    @Override
    public List list(int pid, String type) {
        ProductImageExample productImageExample =new ProductImageExample();
        productImageExample.createCriteria().andPidEqualTo(pid).andTypeEqualTo(type);
        productImageExample.setOrderByClause("id desc");
        return productImageMapper.selectByExample(productImageExample);
    }
}
