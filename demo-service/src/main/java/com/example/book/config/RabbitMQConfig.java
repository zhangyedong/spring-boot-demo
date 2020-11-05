package com.example.book.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbitMQ-topic模式
 * zhangyd
 * 2020/7/18 14:20
 */
@Configuration
public class RabbitMQConfig {

    //交换机四种类型  direct，topic，headers，Fanout

    //绑定键
    public final static String MAN = "topic.man";
    public final static String WOMAN = "topic.woman";

    @Bean
    public Queue firstQueue() {
        return new Queue(MAN);
    }

    @Bean
    public Queue secondQueue() {
        return new Queue(WOMAN);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange("topicExchange");
    }

    /**
     * 把队列和交换机绑定在一起
     */
    @Bean
    public Binding bindingExchangeMessage() {
        return BindingBuilder.bind(secondQueue()).to(exchange()).with(MAN);
    }

    /**
     * 把队列和交换机绑定在一起
     */
    @Bean
    public Binding bindingExchangeMessage2() {
        return BindingBuilder.bind(secondQueue()).to(exchange()).with("topic.#");
    }
}
