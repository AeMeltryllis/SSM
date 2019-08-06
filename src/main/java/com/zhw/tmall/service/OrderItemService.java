package com.zhw.tmall.service;

import com.zhw.tmall.pojo.Order;
import com.zhw.tmall.pojo.OrderItem;

import java.util.List;

public interface OrderItemService {
    void add(OrderItem c);

    void delete(int id);
    void update(OrderItem c);
    OrderItem get(int id);
    List list();

    void fill(List<Order> orderList);

    void fill(Order order);


}
