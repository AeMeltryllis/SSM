package com.zhw.tmall.comparator;
 
import java.util.Comparator;
 
import com.zhw.tmall.pojo.Product;
 
public class ProductSaleCountComparator implements Comparator<Product>{
    /**
     * ProductSaleCountComparator 销量比较器
     把 销量高的放前面
     * @param p1
     * @param p2
     * @return
     */
    @Override
    public int compare(Product p1, Product p2) {
        return p2.getSaleCount()-p1.getSaleCount();
    }
 
}