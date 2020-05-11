package com.example.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * 等级
 * zhangyd
 * 2020/5/11 12:50
 */
@Getter
public enum GradeEnum {

    PRIMARY(1, "小学"),
    SECONDORY(2, "中学"),
    HIGH(3, "高中");

    @EnumValue
    private int code;
    private String value;

    GradeEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }
}
