package com.example.common.enums;

/**
 * 错误枚举
 * zhangyd
 * 2020/4/9 16:43
 */
public enum DemoApiEnum {

    SUCCESS("000000","请求成功"),
    SYSTEM_ERROR("999999","系统异常"),
    TRANSFOR_ERROR("100001","转换异常错误");

    DemoApiEnum(String code, String msg){
        this.code = code;
        this.msg = msg;
    }

    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
