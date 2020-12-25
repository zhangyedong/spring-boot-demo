package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.model.User;
import org.apache.ibatis.annotations.Param;

/**
 * TODO
 * zhangyd
 * 2020/5/11 11:52
 */
public interface UserPlusXmlMapper {

    IPage<User> selectPageByName(Page<?> page, @Param("name") String name);
}
