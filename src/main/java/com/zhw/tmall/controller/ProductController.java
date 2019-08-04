package com.zhw.tmall.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhw.tmall.pojo.Category;
import com.zhw.tmall.pojo.Product;
import com.zhw.tmall.service.CategoryService;
import com.zhw.tmall.service.ProductService;
import com.zhw.tmall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("")
public class ProductController  {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;

    @RequestMapping("admin_product_list")
    public String list (Model model, Page page,int cid){
//        基于cid，获取当前分类下的属性集合
            Category category = categoryService.get(cid);
//            page.setCount(5);
            PageHelper.offsetPage(page.getStart(),page.getCount());

            List<Product> productList = productService.list(cid);
            int total = (int) new PageInfo<>(productList).getTotal();
            page.setTotal(total);
            page.setParam("&cid="+category.getId());


            model.addAttribute("productList", productList);
            model.addAttribute("category", category);
            model.addAttribute("page", page);
        return "admin/listProduct";
    }

    @RequestMapping("admin_product_add")
    public String add (Product product){
        /**输入时间
         *
         */
            product.setCreateDate(new Date());
            productService.add(product);

            return "redirect:admin_product_list?cid="+product.getCid();

    }
    @RequestMapping("admin_product_delete")
    public String delete (int id){

        Product p = productService.get(id);
        productService.delete(id);
        return "redirect:admin_product_list?cid="+p.getCid();


    }
    @RequestMapping("admin_product_edit")
    public String edit (int id ,Model model){
//        Category c = categoryService.get(product.getCid());
//        product.setCategory(c);
//        model.addAttribute("product", product);
//        return "admin/editProduct";
        Product product = productService.get(id);
        Category category = categoryService.get(product.getCid());
        product.setCategory(category);
        model.addAttribute("product", product);
        return "admin/editProduct";


    }
    @RequestMapping("admin_product_update")
    public String update (Product product){
        productService.update(product);
        return "redirect:admin_product_list?cid="+product.getCid();


    }


}
