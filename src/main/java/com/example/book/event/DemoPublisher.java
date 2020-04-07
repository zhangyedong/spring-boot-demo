package com.example.book.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * TODO
 * zhangyd
 * 2020/3/31 22:23
 */
@Component
public class DemoPublisher {

    @Autowired
    ApplicationContext context;

    public void publish(String msg){
        context.publishEvent(new DemoEvent(this,msg));
    }
}
