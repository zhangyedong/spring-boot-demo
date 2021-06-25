package com.design.strategy;

import lombok.AllArgsConstructor;

import java.math.BigDecimal;

/**
 * @author zhangyd
 * @Description: 满减活动
 * @date 2021/6/9
 */
@AllArgsConstructor
public class CashReturn extends AbstractCashSuper {

    /**
     * 满金额
     */
    private static final int ENOUGH_AMOUNT = 500;

    /**
     * 减金额
     */
    private static final int REDUCE_AMOUNT = 50;

    @Override
    public BigDecimal acceptCash(BigDecimal num) {
        if (num.compareTo(new BigDecimal(ENOUGH_AMOUNT)) >= 0) {
            return num.subtract(new BigDecimal(REDUCE_AMOUNT));
        }
        return num;
    }
}
