package com.example.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.example.common.model.dto.BaseResDTO;
import com.example.common.model.dto.req.QueryUserReqDTO;
import com.example.common.model.dto.res.UserInfoBody;
import com.example.service.UserService;
import com.example.service.user.SubUserQuery;
import org.springframework.stereotype.Component;

/**
 * user服务
 * zhangyd
 * 2020/4/9 16:22
 */
@Component
@Service(version = "1.0.0")
public class UserServiceImpl implements UserService {

    private SubUserQuery subUserQuery;

    @Override
    public BaseResDTO<UserInfoBody> queryUserInfo(QueryUserReqDTO reqDTO) {

        return subUserQuery.excution(reqDTO);
    }
}
