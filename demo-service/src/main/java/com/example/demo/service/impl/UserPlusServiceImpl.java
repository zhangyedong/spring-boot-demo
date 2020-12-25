package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.mapper.UserPlusMapper;
import com.example.demo.model.User;
import com.example.demo.service.UserPlusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO
 * zhangyd
 * 2020/4/27 18:37
 */
@Service
public class UserPlusServiceImpl implements UserPlusService {

    @Autowired
    UserPlusMapper userPlusMapper;

    @Override
    public List<User> findUserAll() {
        return userPlusMapper.selectList(null);
    }
}
