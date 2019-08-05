package com.zhw.tmall.service.impl;

import com.zhw.tmall.mapper.CategoryMapper;
import com.zhw.tmall.mapper.ProductMapper;
import com.zhw.tmall.pojo.Category;
import com.zhw.tmall.pojo.Product;
import com.zhw.tmall.pojo.ProductExample;
import com.zhw.tmall.pojo.ProductImage;
import com.zhw.tmall.service.CategoryService;
import com.zhw.tmall.service.ProductImageService;
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
    @Autowired(required = false)
    ProductImageService productImageService;

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
        /*在image映射实现后加入product*/
        setFirstProductImage(product);
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
        /*将很多图片放进详情*/
        setFirstProductImage(productList);
        return productList;
    }

    /**
     * 根据pid和图片类型查询出所有的单个图片，然后把第一个取出来放在firstProductImage上。
     * @param product
     */
    @Override
    public void setFirstProductImage(Product product) {
        List<ProductImage> pis = productImageService.list(product.getId(), ProductImageService.type_single);
        if (!pis.isEmpty()) {
            /*将第一张图片放入*/
            ProductImage pi = pis.get(0);
            product.setFirstProductImage(pi);
        }
    }

    public void setFirstProductImage(List<Product> ps) {
        for (Product p : ps) {
            setFirstProductImage(p);
        }
    }
}
