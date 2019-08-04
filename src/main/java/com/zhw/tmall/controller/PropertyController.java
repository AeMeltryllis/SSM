package com.zhw.tmall.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhw.tmall.pojo.Category;
import com.zhw.tmall.pojo.Property;
import com.zhw.tmall.service.CategoryService;
import com.zhw.tmall.service.PropertyService;
import com.zhw.tmall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.jws.WebParam;
import java.util.List;

@Controller
@RequestMapping("")
public class PropertyController {
    @Autowired
    PropertyService propertyService;
    @Autowired
    CategoryService categoryService;

    @RequestMapping("admin_property_list")
    public String list(int cid, Page page, Model model){
//        基于cid，获取当前分类下的属性集合
        Category category = categoryService.get(cid);

        PageHelper.offsetPage(page.getStart(),page.getCount());

        List<Property> propertyList = propertyService.list(cid);
        int total = (int) new PageInfo<>(propertyList).getTotal();
        page.setTotal(total);
        page.setParam("&cid="+category.getId());

        model.addAttribute("propertyList", propertyList);
        model.addAttribute("category", category);
        model.addAttribute("page", page);


        return "admin/listProperty";
    }
    @RequestMapping("admin_property_update")
    public String update(Property property){
        propertyService.update(property);
//        客户端跳转到admin_property_list,并带上参数cid
        return "redirect:admin_property_list?cid="+property.getCid();
    }

    @RequestMapping("admin_property_edit")
    public String edit(Model model, int id){
        Property property = propertyService.get(id);
        Category category = categoryService.get(property.getCid());
//        将分类放进属性里面
        property.setCategory(category);
        model.addAttribute("property", property);
        return "admin/editProperty";
    }
    @RequestMapping("admin_property_delete")
    public String delete(Property property){
        propertyService.delete(property.getId());
        return "redirect:admin_property_list?cid="+property.getCid();
    }

    @RequestMapping("admin_property_add")
    public String add(Property property){
        propertyService.add(property);
        return "redirect:admin_property_list?cid="+property.getCid();
    }



}
