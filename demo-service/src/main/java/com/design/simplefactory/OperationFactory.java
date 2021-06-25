package com.design.simplefactory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author zhangyd
 * @Description: 简单工厂模式
 * @date 2021/6/8
 */
@Component
@Slf4j
public class OperationFactory {
    public static AbstractOperation initOperation(OperEnum operEnum) {
        AbstractOperation operation = null;
        switch (operEnum) {
            case ADD:
                operation = new AddOperation();
                break;
            case MINUS:
                operation = new MinusOperation();
                break;
            default:
                log.error("OperationFactory 匹配错误");
        }
        return operation;
    }
}
