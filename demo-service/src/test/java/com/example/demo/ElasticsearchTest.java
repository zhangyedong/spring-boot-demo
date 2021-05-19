package com.example.demo;

import com.DemoApplication;
import com.example.demo.model.ElsasticUser;
import com.example.demo.model.User;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.DocumentOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.UpdateQuery;
import org.springframework.data.mongodb.core.DefaultIndexOperations;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

/**
 * ElasticsearchTest
 * zhangyd
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DemoApplication.class})
public class ElasticsearchTest {

    @Autowired
    MongoOperations mongoOperations;

    @Autowired
    RestHighLevelClient restHighLevelClient;

    @Test
    public void test() {
        ElasticsearchRestTemplate elasticsearchRestTemplate = new ElasticsearchRestTemplate(restHighLevelClient);

        ElsasticUser elsasticUser = new ElsasticUser();
        elsasticUser.setId(UUID.randomUUID().toString());
        elsasticUser.setName("李四");
        elsasticUser.setAge(11);
        elasticsearchRestTemplate.save(elsasticUser);
    }

}
