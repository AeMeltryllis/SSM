package com.zhw.tmall.service.impl;
 
import java.util.List;

import com.zhw.tmall.mapper.OrderItemMapper;
import com.zhw.tmall.pojo.Order;
import com.zhw.tmall.pojo.OrderItem;
import com.zhw.tmall.pojo.OrderItemExample;
import com.zhw.tmall.pojo.Product;
import com.zhw.tmall.service.OrderItemService;
import com.zhw.tmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 

 
@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired(required = false)
    OrderItemMapper orderItemMapper;
    @Autowired
    ProductService productService;
 
    @Override
    public void add(OrderItem c) {
        orderItemMapper.insert(c);
    }
 
    @Override
    public void delete(int id) {
        orderItemMapper.deleteByPrimaryKey(id);
    }
 
    @Override
    public void update(OrderItem c) {
        orderItemMapper.updateByPrimaryKeySelective(c);
    }
 
    @Override
    public OrderItem get(int id) {
        OrderItem result = orderItemMapper.selectByPrimaryKey(id);
        setProduct(result);
        return result;
    }
 
    public List<OrderItem> list(){
        OrderItemExample example =new OrderItemExample();
        example.setOrderByClause("id desc");
        return orderItemMapper.selectByExample(example);
 
    }
 
    @Override
    public void fill(List<Order> os) {
        for (Order o : os) {
            fill(o);
        }
    }
 
    public void fill(Order o) {
        OrderItemExample example =new OrderItemExample();
        example.createCriteria().andOidEqualTo(o.getId());
        example.setOrderByClause("id desc");
        List<OrderItem> orderItemList =orderItemMapper.selectByExample(example);

        setProduct(orderItemList );
        /*订单的总计金额*/
        float total = 0;
        /*订单的总计数量*/
        int totalNumber = 0;
        for (OrderItem oi : orderItemList ) {
            total+=oi.getNumber()*oi.getProduct().getPromotePrice();
            totalNumber+=oi.getNumber();
        }
        o.setTotal(total);
        o.setTotalNumber(totalNumber);
        o.setOrderItems(orderItemList );
 
    }

    @Override
    public int getSaleCount(int pid) {
        OrderItemExample example =new OrderItemExample();
        example.createCriteria().andPidEqualTo(pid);
        List<OrderItem> ois =orderItemMapper.selectByExample(example);
        int result =0;
        for (OrderItem oi : ois) {
//            总计销量
            result+=oi.getNumber();
        }
        return result;
    }



    public void setProduct(List<OrderItem> orderItemList ){
        for (OrderItem oi: orderItemList ) {
            setProduct(oi);
        }
    }
 
    private void setProduct(OrderItem oi) {
        Product p = productService.get(oi.getPid());
        oi.setProduct(p);
    }

 

 
}