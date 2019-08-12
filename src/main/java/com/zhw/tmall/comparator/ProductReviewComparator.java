package com.zhw.tmall.comparator;
 
import java.util.Comparator;
 
import com.zhw.tmall.pojo.Product;
 
public class ProductReviewComparator implements Comparator<Product>{
    /**
     * ProductReviewComparator 人气比较器
     把 评价数量多的放前面
     * @param p1
     * @param p2
     * @return
     */
    @Override
    public int compare(Product p1, Product p2) {
        return p2.getReviewCount()-p1.getReviewCount();
    }
 
}