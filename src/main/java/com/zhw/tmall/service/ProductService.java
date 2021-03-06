package com.zhw.tmall.service;
  
import java.util.List;
 
import com.zhw.tmall.pojo.Category;
import com.zhw.tmall.pojo.Product;
 
public interface ProductService {
    void add(Product product);
    void delete(int id);
    void update(Product p);
    Product get(int id);
    List list(int cid);
    /*在实现产品图片时添加*/
    void setFirstProductImage(Product product);

    public void fill(List<Category> categorys);
    public void fill(Category category);
    public void fillByRow(List<Category> categorys);

    void setSaleAndReviewNumber(Product p);

    void setSaleAndReviewNumber(List<Product> ps);
}