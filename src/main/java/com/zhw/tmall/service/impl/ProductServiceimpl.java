package com.zhw.tmall.service.impl;

import com.zhw.tmall.mapper.CategoryMapper;
import com.zhw.tmall.mapper.ProductMapper;
import com.zhw.tmall.pojo.Category;
import com.zhw.tmall.pojo.Product;
import com.zhw.tmall.pojo.ProductExample;
import com.zhw.tmall.pojo.ProductImage;
import com.zhw.tmall.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    ReviewService reviewService;


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

    @Override
    /*为分类填充产品集合*/
    public void fill(List<Category> categorys) {
        for (Category category:categorys
             ) {
            fill(category);
            
        }

    }

    @Override
    /*为多个分类填充产品集合*/
    public void fill(Category category) {
        List<Product> productList = list(category.getId());
        category.setProducts(productList);

    }

    @Override
    /*为多个分类填充推荐产品集合，即把分类下的产品集合，按照8个为一行，拆成多行，以利于后续页面上进行显示*/
    public void fillByRow(List<Category> categorys) {
        /*推荐产品数目*/
        int productNumberEachRow = 8;
        for (Category c : categorys) {
            List<Product> products =  c.getProducts();
            /*ArrayList<>方便查找*/
            List<List<Product>> productsByRow =  new ArrayList<>();
            for (int i = 0; i < products.size(); i+=productNumberEachRow) {
                int size = i+productNumberEachRow;
                size= size>products.size()?products.size():size;
                List<Product> productsOfEachRow =products.subList(i, size);
                productsByRow.add(productsOfEachRow);
            }
            c.setProductsByRow(productsByRow);
        }

    }

    @Override
    public void setSaleAndReviewNumber(Product p) {
        int saleCount = orderItemService.getSaleCount(p.getId());
        p.setSaleCount(saleCount);

        int reviewCount = reviewService.getCount(p.getId());
        p.setReviewCount(reviewCount);
    }


    @Override
    public void setSaleAndReviewNumber(List<Product> ps) {
        for (Product p : ps) {
            setSaleAndReviewNumber(p);
        }
    }

    public void setFirstProductImage(List<Product> ps) {
        for (Product p : ps) {
            setFirstProductImage(p);
        }
    }
}
