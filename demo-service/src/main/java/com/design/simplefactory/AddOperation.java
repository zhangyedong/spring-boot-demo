package com.design.simplefactory;


/**
 * @author zhangyd
 * @Description: 加法操作
 * @date 2021/6/8
 */
public class AddOperation extends AbstractOperation {


    @Override
    public Double getResult(Double a, Double b) {
        return a + b;
    }
}
