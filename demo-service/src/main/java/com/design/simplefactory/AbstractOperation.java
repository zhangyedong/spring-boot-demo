package com.design.simplefactory;

/**
 * @author zhangyd
 * @Description: 操作类
 * @date 2021/6/8
 */
public abstract class AbstractOperation {

    /**
     * 计算得到结果
     *
     * @param a 数字1
     * @param b 数字2
     * @return 结果
     */
    public abstract Double getResult(Double a, Double b);
}
