//package com.example.book.mq;
//
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
///**
// * mq接收
// * zhangyd
// * 2020/4/13 15:59
// */
//@Component
//public class MqReceiver {
//
//    @RabbitListener(queues = {"my-queue"})
//    public void reveiveMessage(String message) {
//        System.out.println("rabbitMQ 接收到信息：" + message);
//    }
//}
