package com.zhw.tmall.service.impl;

import com.zhw.tmall.mapper.ReviewMapper;
import com.zhw.tmall.pojo.Review;
import com.zhw.tmall.pojo.ReviewExample;
import com.zhw.tmall.pojo.User;
import com.zhw.tmall.service.ReviewService;
import com.zhw.tmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired(required = false)
    ReviewMapper reviewMapper;
    @Autowired
    UserService userService;

    @Override
    public void add(Review c) {
        reviewMapper.insert(c);

    }

    @Override
    public void delete(int id) {
        reviewMapper.deleteByPrimaryKey(id);

    }

    @Override
    public void update(Review c) {
        reviewMapper.updateByPrimaryKey(c);

    }

    @Override
    public Review get(int id) {
        return reviewMapper.selectByPrimaryKey(id);
    }

    @Override
    public List list(int pid) {
        ReviewExample reviewExample =new ReviewExample();
        reviewExample.createCriteria().andPidEqualTo(pid);
        List<Review> result =reviewMapper.selectByExample(reviewExample);
        setUser(result);

        return result;
    }
    public void setUser(List<Review> reviews){
        for (Review review : reviews) {
            setUser(review);
        }
    }

    private void setUser(Review review) {
        int uid = review.getUid();
        User user = userService.get(uid);
        review.setUser(user);
    }

    /**
     * 通过逆向工程生成的代码获取count的方法
     * @param pid
     * @return
     */
    @Override
    public int getCount(int pid) {

        return list(pid).size();
    }


    public int count(int pid) {

        return reviewMapper.count(pid);

    }
}
