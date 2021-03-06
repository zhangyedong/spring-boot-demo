package com.example.common.exception;

import com.example.common.enums.DemoApiEnum;
import lombok.Data;

/**
 * base异常
 * zhangyd
 * 2020/4/9 17:19
 */
@Data
public class BaseRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 3930960263658593237L;

    private String code;
    private String message;

    public BaseRuntimeException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public BaseRuntimeException(DemoApiEnum demoApiEnum) {
        this.code = demoApiEnum.getCode();
        this.message = demoApiEnum.getMsg();
    }
}
