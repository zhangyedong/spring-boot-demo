package com.example.demo.mapper;

import com.example.demo.model.User;
import org.apache.ibatis.annotations.Param;

/**
 * TODO
 * zhangyd
 * 2020/3/29 18:32
 */
public interface UserMapper {

    User getUser(@Param("name") String name,@Param("pwd") String pwd);

}
