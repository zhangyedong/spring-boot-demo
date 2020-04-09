package com.example.book.aop;

import org.springframework.stereotype.Service;

/**
 * TODO
 * zhangyd
 * 2020/3/31 23:21
 */
@Service
public class DemoAnnotationService {

    @ActionLog("注解式的拦截add方法")
    public void add(){
        System.out.println("enter add method...");
    }
}
