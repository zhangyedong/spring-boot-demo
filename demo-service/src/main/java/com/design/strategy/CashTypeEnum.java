package com.design.strategy;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zhangyd
 * @Description: 金额操作类型
 * @date 2021/6/10
 */
@Getter
@AllArgsConstructor
public enum CashTypeEnum {

    /**
     *
     */
    NORMAL(1,"正常"),
    REBATE(2,"折扣"),
    RETURN(3,"满减");

    private int code;
    private String desc;

}
