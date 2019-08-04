package com.zhw.tmall.service.impl;

import com.zhw.tmall.mapper.CategoryMapper;
import com.zhw.tmall.mapper.ProductMapper;
import com.zhw.tmall.pojo.Category;
import com.zhw.tmall.pojo.Product;
import com.zhw.tmall.pojo.ProductExample;
import com.zhw.tmall.service.CategoryService;
import com.zhw.tmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * get和list方法都会把取出来的Product对象设置上对应的category
 */
@Service
public class ProductServiceimpl  implements ProductService{
    @Autowired(required = false)
    ProductMapper productMapper;
    @Autowired(required = false)
    CategoryService categoryService;

    @Override
    public void add(Product product) {
        productMapper.insert(product);
    }

    @Override
    public void delete(int id) {
        productMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Product product) {
//updateByPrimaryKeySelective和updateByPrimaryKey的区别？
        productMapper.updateByPrimaryKeySelective(product);

    }

    /**
     *用意？get和list方法都会把取出来的Product对象设置上对应的category
     * @param id
     * @return
     */
    @Override
    public Product get(int id) {
        Product product = productMapper.selectByPrimaryKey(id);
        setCategory(product);
        return product ;
    }
    public void setCategory(List<Product> productList){
        for (Product p : productList)
            setCategory(p);
    }
    public void setCategory(Product product){
        int cid = product.getCid();
        Category c = categoryService.get(cid);
        product.setCategory(c);
    }


    @Override
    public List<Product> list(int cid) {
        ProductExample example = new ProductExample();
        example.createCriteria().andCidEqualTo(cid);
        example.setOrderByClause("id desc");
        List <Product> productList = productMapper.selectByExample(example);
        setCategory(productList);
        return productList;
    }
}
