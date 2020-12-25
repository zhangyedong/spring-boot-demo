package com.example.demo.model;

import com.example.common.enums.GradeEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;

/**
 * user
 * zhangyd
 * 2020/3/29 18:29
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 6110460440342354746L;

    private Integer id;

    private String name;

    private String password;

    private String phoneNum;

    private GradeEnum grade;
}
