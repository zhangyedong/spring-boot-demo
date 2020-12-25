package com.example.controller.http;

import com.example.common.enums.DemoApiEnum;
import com.example.common.model.dto.BaseResDTO;
import com.example.common.model.dto.Head;
import com.example.common.model.dto.req.QueryUserReqDTO;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * user
 * zhangyd
 * 2020/4/27 10:11
 */
@RestController
@RequestMapping("/user")
@Api(value = "用户接口", tags = "用户接口")
public class UserApiController {

    @Autowired
    UserService userService;

    @ApiOperation(value = "查询所有用户", notes = "查询所有用户")
    @PostMapping("/findAllUser")
    public BaseResDTO<User> findAllUser(@RequestBody QueryUserReqDTO reqDTO) {
        BaseResDTO<User> res = new BaseResDTO<>();
        User user = userService.getUserInfo(reqDTO.getName(), reqDTO.getPassword());
        if (user != null) {
            res.setBody(user);
        }
        res.setHead(new Head(DemoApiEnum.SUCCESS));
        return res;
    }
}
