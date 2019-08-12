package com.zhw.tmall.comparator;
 
import java.util.Comparator;
 
import com.zhw.tmall.pojo.Product;
 
public class ProductPriceComparator implements Comparator<Product>{
    /**
     * ProductPriceComparator 价格比较器
     把 价格低的放前面
     * @param p1
     * @param p2
     * @return
     */
    @Override
    public int compare(Product p1, Product p2) {
        return (int) (p1.getPromotePrice()-p2.getPromotePrice());
    }
 
}