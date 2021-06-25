package com.design.strategy;

import java.math.BigDecimal;

/**
 * @author zhangyd
 * @Description: 折扣
 * @date 2021/6/9
 */
public class CashRebate extends AbstractCashSuper {

    /**
     * 折扣率
     */
    private static final String RATE = "0.8";

    /**
     * 折扣率 0.8
     */
    @Override
    public BigDecimal acceptCash(BigDecimal num) {
        return num.multiply(new BigDecimal(RATE));
    }
}
