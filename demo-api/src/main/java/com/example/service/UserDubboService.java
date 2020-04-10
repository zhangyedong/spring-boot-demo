package com.example.service;

import com.example.common.model.dto.BaseResDTO;
import com.example.common.model.dto.req.QueryUserReqDTO;
import com.example.common.model.dto.res.UserInfoBody;

/**
 * dto
 * zhangyd
 * 2020/4/9 15:03
 */
public interface UserDubboService {

    /**
     * 查询用户信息
     */
    BaseResDTO<UserInfoBody> queryUserInfo(QueryUserReqDTO reqDTO);
}
