package com.example.service.user;

import com.example.common.enums.DemoApiEnum;
import com.example.common.exception.BaseRuntimeException;
import com.example.common.model.dto.BaseReqDTO;
import com.example.common.model.dto.BaseResDTO;
import com.example.common.model.dto.Head;
import com.example.common.model.dto.req.QueryUserReqDTO;
import com.example.common.model.dto.res.UserInfoBody;
import com.example.common.model.entity.UserEntity;
import com.example.repository.UserRepository;
import com.example.service.AbstractDemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 用户业务
 * zhangyd
 * 2020/4/9 16:56
 */
@Component
@Slf4j
public class SubUserQuery extends AbstractDemoService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void checkParams(BaseReqDTO baseReqDTO) {
        QueryUserReqDTO reqDTO;
        if (baseReqDTO instanceof QueryUserReqDTO) {
            reqDTO = (QueryUserReqDTO) baseReqDTO;
        } else {
            log.info("实体转换异常");
            throw new BaseRuntimeException(DemoApiEnum.TRANSFOR_ERROR);
        }
    }

    @Override
    public BaseResDTO<UserInfoBody> excution(BaseReqDTO baseReqDTO) {

        QueryUserReqDTO reqDTO = (QueryUserReqDTO) baseReqDTO;
        BaseResDTO<UserInfoBody> bodyBaseResDTO = new BaseResDTO<>();
        if (!StringUtils.isEmpty(reqDTO.getName())) {
            UserEntity user = userRepository.findAllByName(reqDTO.getName());
            UserInfoBody body = new UserInfoBody();
            body.setName(user.getName());
            body.setPassword(user.getPassword());
            body.setPhone_num(user.getPhoneNum());
            bodyBaseResDTO.setBody(body);
        }
        bodyBaseResDTO.setHead(new Head(DemoApiEnum.SUCCESS));
        return bodyBaseResDTO;
    }
}
