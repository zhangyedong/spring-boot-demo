package com.example.book.annotation;

import org.springframework.stereotype.Service;

/**
 * TODO
 * zhangyd
 * 2020/4/1 17:06
 */
@Service
public class DemoService {
    public void out(){
        System.out.println("从组合组件获取到的bean。。。。");
    }
}
