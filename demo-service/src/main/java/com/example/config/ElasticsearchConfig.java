//package com.example.config;
//
//import org.elasticsearch.client.RestHighLevelClient;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.elasticsearch.client.ClientConfiguration;
//import org.springframework.data.elasticsearch.client.RestClients;
//import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
//
///**
// * @author zhangyd
// * @Description: elasticsearch 配置
// * @date 2021/5/19
// */
//@Configuration
//public class ElasticsearchConfig extends AbstractElasticsearchConfiguration {
//
//    @Bean("restHighLevelClient")
//    @Override
//    public RestHighLevelClient elasticsearchClient() {
//        return RestClients.create(ClientConfiguration.create("localhost:9200")).rest();
//    }
//}
