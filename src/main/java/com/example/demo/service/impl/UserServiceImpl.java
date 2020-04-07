package com.example.demo.service.impl;

import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * TODO
 * zhangyd
 * 2020/3/29 18:33
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserInfo(String name, String pwd) {
        return userMapper.getUser(name, pwd);
    }
}
