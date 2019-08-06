package com.zhw.tmall.service.impl;
 
import java.util.List;

import com.zhw.tmall.mapper.OrderMapper;
import com.zhw.tmall.pojo.Order;
import com.zhw.tmall.pojo.OrderExample;
import com.zhw.tmall.pojo.User;
import com.zhw.tmall.service.OrderService;
import com.zhw.tmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired(required = false)
    OrderMapper orderMapper;
 
    @Autowired
    UserService userService;
 
    @Override
    public void add(Order c) {
        orderMapper.insert(c);
    }
 
    @Override
    public void delete(int id) {
        orderMapper.deleteByPrimaryKey(id);
    }
 
    @Override
    public void update(Order c) {
        orderMapper.updateByPrimaryKeySelective(c);
    }
 
    @Override
    public Order get(int id) {
        return orderMapper.selectByPrimaryKey(id);
    }
 
    public List<Order> list(){
        OrderExample example =new OrderExample();
        example.setOrderByClause("id desc");
        List<Order> result =orderMapper.selectByExample(example);
        setUser(result);
        return result;
    }
    public void setUser(List<Order> os){
        for (Order o : os)
            setUser(o);
    }
    public void setUser(Order o){
        int uid = o.getUid();
        User u = userService.get(uid);
        o.setUser(u);
    }
 
}