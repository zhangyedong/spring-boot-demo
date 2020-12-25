package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.model.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * mybatis-plus
 * zhangyd
 * 2020/4/27 17:09
 */
public interface UserPlusMapper extends BaseMapper<User> {

    @Select("select * from user where name = #{name}")
    List<User> selectAllByName(@Param("name") String name);

}
