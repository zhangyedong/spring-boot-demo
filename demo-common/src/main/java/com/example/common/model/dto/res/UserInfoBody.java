package com.example.common.model.dto.res;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * user body
 * zhangyd
 * 2020/4/9 15:51
 */
@Data
public class UserInfoBody implements Serializable {

    @JSONField(name = "name")
    private String name;

    @JSONField(name = "password")
    private String password;

    @JSONField(name = "phone_num")
    private String phone_num;


}
