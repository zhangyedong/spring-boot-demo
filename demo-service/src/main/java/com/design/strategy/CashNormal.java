package com.design.strategy;

import java.math.BigDecimal;

/**
 * @author zhangyd
 * @Description: 正常收取
 * @date 2021/6/9
 */
public class CashNormal extends AbstractCashSuper {
    @Override
    public BigDecimal acceptCash(BigDecimal num) {
        return num;
    }
}
