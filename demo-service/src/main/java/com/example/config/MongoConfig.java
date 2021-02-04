package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;

/**
 * mongoDB
 * zhangyd
 * 2021/2/3 23:12
 */
@Configuration
public class MongoConfig {

    @Bean
    public MongoClientFactoryBean mongo() {
        MongoClientFactoryBean bean = new MongoClientFactoryBean();
        bean.setHost("127.0.0.1");
        return bean;
    }
}
