package com.zhw.tmall.controller;
 
import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.zhw.tmall.pojo.Order;
import com.zhw.tmall.service.OrderItemService;
import com.zhw.tmall.service.OrderService;
import com.zhw.tmall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
 
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

 
@Controller
@RequestMapping("")
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    OrderItemService orderItemService;
 
    @RequestMapping("admin_order_list")
    public String list(Model model, Page page){
        PageHelper.offsetPage(page.getStart(),page.getCount());
 
        List<Order> orderList= orderService.list();
 
        int total = (int) new PageInfo<>(orderList).getTotal();
        page.setTotal(total);
 
        orderItemService.fill(orderList);
 
        model.addAttribute("os", orderList);
        model.addAttribute("page", page);
 
        return "admin/listOrder";
    }
 
    @RequestMapping("admin_order_delivery")
    public String delivery(Order o) throws IOException {
        o.setDeliveryDate(new Date());
        o.setStatus(OrderService.waitConfirm);
        orderService.update(o);
        return "redirect:admin_order_list";
    }
}