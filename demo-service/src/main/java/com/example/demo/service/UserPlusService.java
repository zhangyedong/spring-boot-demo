package com.example.demo.service;

import com.example.demo.model.User;

import java.util.List;

/**
 * mybatis-plus service
 * zhangyd
 * 2020/4/27 18:36
 */
public interface UserPlusService {
    List<User> findUserAll();
}
