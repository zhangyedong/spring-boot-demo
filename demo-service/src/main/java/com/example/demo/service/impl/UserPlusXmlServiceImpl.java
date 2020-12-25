package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.mapper.UserPlusXmlMapper;
import com.example.demo.model.User;
import com.example.demo.service.UserPlusXmlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 分页插件
 * zhangyd
 * 2020/5/11 12:18
 */
@Service
public class UserPlusXmlServiceImpl implements UserPlusXmlService {

    @Autowired
    UserPlusXmlMapper userPlusXmlMapper;

    @Override
    public IPage<User> findPageByName(Page<User> page, String name) {
        return userPlusXmlMapper.selectPageByName(page,name);
    }
}
