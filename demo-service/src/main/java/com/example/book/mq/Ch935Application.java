//package com.example.book.mq;
//
//import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
//
//
///**
// * rabbitmq
// * zhangyd
// * 2020/4/13 15:49
// */
//@SpringBootApplication
//public class Ch935Application implements CommandLineRunner {
//
//    @Autowired
//    RabbitTemplate rabbitTemplate;
//
//    public static void main(String[] args) {
//        SpringApplication.run(Ch935Application.class, args);
//    }
//
//    @Bean
//    public Queue myQueue() {
//        return new Queue("my-queue");
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        rabbitTemplate.convertAndSend("my-queue", "来自RabbitMQ的问候...");
//    }
//}
