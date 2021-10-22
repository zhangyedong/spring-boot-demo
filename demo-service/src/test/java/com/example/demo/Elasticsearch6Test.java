package com.example.demo;

import com.DemoApplication;
import com.alibaba.fastjson.JSON;
import com.example.demo.model.ElsasticUser;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.search.MatchQuery;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.UUID;

/**
 * ElasticsearchTest 6.8
 * zhangyd
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DemoApplication.class})
@Slf4j
public class Elasticsearch6Test {

//    @Autowired
//    RestHighLevelClient restHighLevelClient;

    @Autowired
    TransportClient transportClient;

    @Test
    public void test() {

        //新增
        ElsasticUser elsasticUser = new ElsasticUser();
        elsasticUser.setId(UUID.randomUUID().toString());
        elsasticUser.setName("张三");
        elsasticUser.setAge(13);
        elsasticUser.setGender("男");

        String indexName = "elsastic-user"+"-"+ LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);
        IndexRequestBuilder create  = transportClient.prepareIndex(indexName,"elsastic-user")
                .setSource(JSON.toJSONString(elsasticUser), XContentType.JSON);
        IndexResponse indexResponse = create.execute().actionGet();
        log.info("save es result:{}",indexResponse);

        //修改
        UpdateResponse updateResponse = transportClient.prepareUpdate(indexName,
                "elsastic-user",
                "id")
                .setDoc(JSON.toJSONString(elsasticUser),XContentType.JSON).get();
        log.info("update es result:{}",updateResponse);



        //匹配查询
        QueryBuilders.matchQuery("name","zhang")
                .operator(Operator.AND)
                .zeroTermsQuery(MatchQuery.ZeroTermsQuery.ALL);

        //分页-不推荐使用
        transportClient.prepareSearch("elsastic-user")
                .setQuery(QueryBuilders.matchAllQuery())
                .setFrom(1)
                .setSize(20)
                .execute().actionGet();

        //排序
        transportClient.prepareSearch("elsastic-user")
                .setQuery(QueryBuilders.matchAllQuery())
                .setFrom(1)
                .setSize(20).addSort(SortBuilders.fieldSort("age").order(SortOrder.DESC))
                .execute().actionGet();
    }

}
