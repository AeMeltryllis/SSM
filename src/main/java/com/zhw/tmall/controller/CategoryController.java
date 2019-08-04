package com.zhw.tmall.controller;



import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhw.tmall.pojo.Category;
import com.zhw.tmall.service.CategoryService;
import com.zhw.tmall.util.ImageUtil;
import com.zhw.tmall.util.Page;
import com.zhw.tmall.util.UploadedImageFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("") //@RequestMapping("")表示访问的时候无需额外的地址
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @RequestMapping("admin_category_list")
    public String list(Model model, Page page){ //为什么这里不用ModelAndView了？
//        List<Category> categoryslist = categoryService.list(page);
//        int total = categoryService.total();
//        page.setTotal(total);
//        model.addAttribute("categoryslist",categoryslist);
//        model.addAttribute("page",page);
//        return "admin/listCategory";

        PageHelper.offsetPage(page.getStart(),page.getCount());
        List<Category> categories= categoryService.list();
//        PageInfo<>(categories)为什么调用getTotal()
        int total = (int) new PageInfo<>(categories).getTotal();
        page.setTotal(total);
        model.addAttribute("categoryslist", categories);
        model.addAttribute("page", page);
        return "admin/listCategory";

    }

    @RequestMapping("admin_category_add")
    public String add(Category category, HttpSession session, UploadedImageFile uploadedImageFile) throws IOException {
        categoryService.add(category);
//        getRealPath返回
        File imageFolder= new File(session.getServletContext().getRealPath("img/category"));
//        通过给定的父抽象路径名和子路径名字符串创建一个新的File实例。
        File file = new File(imageFolder,category.getId()+".jpg");
        if(!file.getParentFile().exists())
            file.getParentFile().mkdirs();

        uploadedImageFile.getImage().transferTo(file);
        BufferedImage img = ImageUtil.change2jpg(file);
        ImageIO.write(img, "jpg", file);
        return "redirect:/admin_category_list";
    }
    @RequestMapping("admin_category_delete")
    public String delete(int id ,HttpSession session){
        categoryService.delete(id);
        File  imageFolder= new File(session.getServletContext().getRealPath("img/category"));
        File file = new File(imageFolder,id+".jpg");
        file.delete();


        return "redirect:/admin_category_list";
    }

    @RequestMapping("admin_category_edit")
    public String edit(int id,Model model) throws IOException {
        Category category= categoryService.get(id);
        model.addAttribute("aCategory", category);
        return "admin/editCategory";
    }
    @RequestMapping("admin_category_update")
    public String update(Category category, HttpSession session, UploadedImageFile uploadedImageFile) throws IOException {
        categoryService.update(category);
        /**
         * 图片上传相关代码
         */
        MultipartFile image = uploadedImageFile.getImage();
        if(null!=image &&!image.isEmpty()){
            File  imageFolder= new File(session.getServletContext().getRealPath("img/category"));
            File file = new File(imageFolder,category.getId()+".jpg");
            image.transferTo(file);
            BufferedImage img = ImageUtil.change2jpg(file);
            ImageIO.write(img, "jpg", file);
        }
        return "redirect:/admin_category_list";
    }
}
