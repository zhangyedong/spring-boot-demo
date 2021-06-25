package com.design.simplefactory;

/**
 * @author zhangyd
 * @Description: 减法操作
 * @date 2021/6/8
 */
public class MinusOperation extends AbstractOperation {
    @Override
    public Double getResult(Double a, Double b) {
        return a - b;
    }
}
