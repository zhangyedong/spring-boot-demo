package com.example.common.model.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * basereq
 * zhangyd
 * 2020/4/9 15:10
 */
@Data
public class BaseReqDTO implements Serializable {

    @NotBlank(message = "流水号不能为空")
    @JSONField(name = "client_serial_no")
    private String clientSerialNo;
}
