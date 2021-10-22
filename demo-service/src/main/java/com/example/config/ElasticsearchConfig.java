package com.example.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

import java.net.InetAddress;

/**
 * @author zhangyd
 * @Description: elasticsearch 配置
 * @date 2021/5/19
 */
@Configuration
public class ElasticsearchConfig {

    //7.x
//    @Bean("restHighLevelClient")
//    @Override
//    public RestHighLevelClient elasticsearchClient() {
//        return RestClients.create(ClientConfiguration.create("localhost:9200")).rest();
//    }

    //6.8
    @Bean
    public TransportClient getConnection() throws Exception {
        // 设置集群名称
        Settings settings = Settings.builder().put("cluster.name", "elasticsearch").build();
        // 创建client
        return new PreBuiltTransportClient(settings)
                .addTransportAddresses(new TransportAddress(InetAddress.getByName("localhost"), 9200));
    }
}
