package com.example.common.model.dto.req;

import com.alibaba.fastjson.annotation.JSONField;
import com.example.common.model.dto.BaseReqDTO;
import lombok.Data;

/**
 * 查询user
 * zhangyd
 * 2020/4/9 15:46
 */
@Data
public class QueryUserReqDTO extends BaseReqDTO {

    private static final long serialVersionUID = 3124702837539533184L;

    @JSONField(name = "id")
    private String id;

    @JSONField(name = "name")
    private String name;

    @JSONField(name = "password")
    private String password;

    @JSONField(name = "phone_num")
    private String phoneNum;
}
