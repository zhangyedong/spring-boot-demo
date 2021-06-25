package com.example.demo;

import com.DemoApplication;
import com.design.simplefactory.AbstractOperation;
import com.design.simplefactory.OperEnum;
import com.design.simplefactory.OperationFactory;
import com.design.strategy.AbstractCashSuper;
import com.design.strategy.CashContext;
import com.design.strategy.CashTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

/**
 * @author zhangyd
 * @Description: 设计模式测试
 * @date 2021/6/8
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DemoApplication.class})
@Slf4j
public class DesignTest {

    /**
     * 简单工厂模式
     */
    @Test
    public void simpleFactoryTest() {
        double a = 5.6;
        double b = 7.7;
        AbstractOperation operation = OperationFactory.initOperation(OperEnum.MINUS);
        Double result = operation.getResult(a, b);
        log.info("result:{}", result);
    }

    /**
     * 策略模式
     */
    @Test
    public void strategy() {
        AbstractCashSuper cashSuper = CashContext.cashContext(CashTypeEnum.REBATE);
        BigDecimal result = cashSuper.acceptCash(new BigDecimal("1000"));
        log.info("result:{}", result);
    }
}
