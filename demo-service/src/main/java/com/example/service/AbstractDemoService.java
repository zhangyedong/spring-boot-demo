package com.example.service;

import com.example.common.exception.BaseRuntimeException;
import com.example.common.model.dto.BaseReqDTO;
import com.example.common.model.dto.BaseResDTO;
import com.example.common.model.dto.res.UserInfoBody;
import lombok.extern.slf4j.Slf4j;

/**
 * 抽象类
 * zhangyd
 * 2020/4/9 16:36
 */
@Slf4j
public abstract class AbstractDemoService {

    public BaseResDTO<UserInfoBody> invoke(BaseReqDTO baseReqDTO) {
        BaseResDTO baseResDTO = null;
        try {
            //请求前业务处理

            checkParams(baseReqDTO);
            baseResDTO = excution(baseReqDTO);
            //返回业务处理
        } catch (Exception e) {
            log.error("系统异常，返回错误");
            baseResDTO = new BaseResDTO(((BaseRuntimeException) e).getCode(), e.getMessage());
        }
        return baseResDTO;
    }

    public abstract void checkParams(BaseReqDTO baseReqDTO);

    public abstract BaseResDTO excution(BaseReqDTO baseReqDTO);

}
