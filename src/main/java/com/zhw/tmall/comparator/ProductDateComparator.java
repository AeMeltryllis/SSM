package com.zhw.tmall.comparator;
 
import java.util.Comparator;
 
import com.zhw.tmall.pojo.Product;
 
public class ProductDateComparator implements Comparator<Product>{
    /**
     * ProductDateComparator 新品比较器
     把 创建日期晚的放前面
     * @param p1
     * @param p2
     * @return
     */
    @Override
    public int compare(Product p1, Product p2) {
        return p2.getCreateDate().compareTo(p1.getCreateDate());
    }
 
}