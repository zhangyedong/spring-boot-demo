package com.design.strategy;

import java.math.BigDecimal;

/**
 * @author zhangyd
 * @Description: 策略模式-抽象类
 * @date 2021/6/9
 */
public abstract class AbstractCashSuper {

    /**
     * 收取现金
     *
     * @param num 金额
     * @return 活动或折扣后金额
     */
    public abstract BigDecimal acceptCash(BigDecimal num);
}
