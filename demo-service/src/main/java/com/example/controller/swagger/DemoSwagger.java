package com.example.controller.swagger;

import com.example.common.model.dto.BaseResDTO;
import com.example.common.model.dto.req.QueryUserReqDTO;
import com.example.service.UserDubboService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * swagger
 * zhangyd
 * 2020/4/25 22:58
 */
@RestController
@Slf4j
@RequestMapping("/api/v1/demo")
@Api(value = "测试类接口", tags = {""})
public class DemoSwagger {
    @Autowired
    UserDubboService userDubboService;

    @ApiOperation(value = "获取用户")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "client_serial_no", value = "流水号", required = true, dataType = "String", defaultValue = "2018122414390000"),
            @ApiImplicitParam(paramType = "query", name = "id", value = "id", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "name", value = "姓名", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "phone_num", value = "手机号", dataType = "String")
    })
    @PostMapping("/getUserAll")
    public String getUserAll(@ApiIgnore @ModelAttribute QueryUserReqDTO queryUserReqDTO) {
        log.info("getUserAll请求参数:" + queryUserReqDTO.toString());
        BaseResDTO baseResDto = userDubboService.queryUserInfo(queryUserReqDTO);
        log.info("getUserAll返回参数:" + baseResDto.toString());
        return baseResDto.toString();
    }
}
