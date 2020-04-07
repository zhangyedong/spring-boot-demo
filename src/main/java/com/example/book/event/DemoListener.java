package com.example.book.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * TODO
 * zhangyd
 * 2020/3/31 22:19
 */
@Component
public class DemoListener implements ApplicationListener<DemoEvent> {
    @Override
    public void onApplicationEvent(DemoEvent demoEvent) {
        String msg = demoEvent.getMsg();

        System.out.println("DemoListener 收到信息 demoPubliser发布的消息:"+msg);
    }
}
