package com.zhw.tmall.service;

import com.zhw.tmall.pojo.User;

import java.util.List;

public interface UserService {
    void add(User c);
    void delete(int id);
    void update(User c);
    User get(int id);
    List list();
//    前台确认用户名是否已存在
    boolean isExist(String name);

    User get(String name, String password);

}
