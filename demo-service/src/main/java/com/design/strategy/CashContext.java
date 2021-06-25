package com.design.strategy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author zhangyd
 * @Description: cash上下文
 * @date 2021/6/10
 */
@Component
@Slf4j
public class CashContext {

    public static AbstractCashSuper cashContext(CashTypeEnum cashType) {
        AbstractCashSuper cashSuper = null;
        switch (cashType) {
            case NORMAL:
                cashSuper = new CashNormal();
                break;
            case REBATE:
                cashSuper = new CashRebate();
                break;
            case RETURN:
                cashSuper = new CashReturn();
                break;
            default:
                log.error("CashContext 匹配异常");
        }
        return cashSuper;
    }
}
