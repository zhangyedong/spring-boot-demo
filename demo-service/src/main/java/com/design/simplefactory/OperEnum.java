package com.design.simplefactory;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zhangyd
 * @Description: 操作类型
 * @date 2021/6/8
 */
@Getter
@AllArgsConstructor
public enum OperEnum {

    /**
     *
     */
    ADD("add","加法"),
    MINUS("minus","减法");

    private String code;
    private String desc;

}
