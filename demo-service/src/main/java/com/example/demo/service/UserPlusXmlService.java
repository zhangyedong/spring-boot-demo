package com.example.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.model.User;

/**
 * 分页插件
 * zhangyd
 * 2020/5/11 12:13
 */
public interface UserPlusXmlService {

    IPage<User> findPageByName(Page<User> page, String name);
}
