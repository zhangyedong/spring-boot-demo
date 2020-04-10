package com.example.common.model.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.example.common.enums.ErrorEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * head
 * zhangyd
 * 2020/4/9 15:30
 */
@Data
public class Head implements Serializable {

    private static final long serialVersionUID = -8219939302111137445L;

    @JSONField(name = "code")
    private String code;

    @JSONField(name = "msg")
    private String msg;

    /**
     * 服务器流水号
     */
    @JSONField(name = "server_serial_no")
    private String serverSerialNo;

    /**
     * 服务器日期 yyyy-MM-dd
     */
    @JSONField(name = "server_date")
    private String serverDate;

    /**
     * 服务器时间 HH:mm:ss
     */
    @JSONField(name = "server_time")
    private String serverTime;

    public Head() {
        this.code = ErrorEnum.SYSTEM_ERROR.getCode();
        this.msg = ErrorEnum.SYSTEM_ERROR.getMsg();
        this.serverSerialNo = String.valueOf(System.currentTimeMillis());
    }

    public Head(ErrorEnum errorEnum) {
        this.code = errorEnum.getCode();
        this.msg = errorEnum.getMsg();
        this.serverSerialNo = String.valueOf(System.currentTimeMillis());
    }
}
