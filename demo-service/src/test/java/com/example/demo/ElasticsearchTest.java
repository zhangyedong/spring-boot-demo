package com.example.demo;

import com.DemoApplication;
import com.example.demo.model.ElsasticUser;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregator;
import org.elasticsearch.search.aggregations.metrics.AvgAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.MaxAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.ParsedAvg;
import org.elasticsearch.search.aggregations.metrics.ParsedMax;
import org.elasticsearch.search.aggregations.metrics.ParsedSum;
import org.elasticsearch.search.aggregations.metrics.SumAggregationBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;
import java.util.UUID;

/**
 * ElasticsearchTest
 * zhangyd
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DemoApplication.class})
public class ElasticsearchTest {

    @Autowired
    RestHighLevelClient restHighLevelClient;

    @Test
    public void test() {
        ElasticsearchRestTemplate elasticsearchRestTemplate = new ElasticsearchRestTemplate(restHighLevelClient);
        //新增
        ElsasticUser elsasticUser = new ElsasticUser();
//        elsasticUser.setId(UUID.randomUUID().toString());
//        elsasticUser.setName("中性");
//        elsasticUser.setAge(13);
//        elsasticUser.setGender("中");
//        elasticsearchRestTemplate.save(elsasticUser);

        //根据id删除
//        elasticsearchRestTemplate.delete("05d93d21-b6bb-413a-b6f7-49e7a7e9ee8c",ElsasticUser.class);

        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        //分词匹配-默认
//        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("age", "11");
//        nativeSearchQueryBuilder.withQuery(matchQueryBuilder);
        //不分词查询
//        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("age", 12);
//        nativeSearchQueryBuilder.withQuery(termQueryBuilder);


        //分词查询多匹配，采用默认的分词器
//        QueryBuilder queryBuilder= QueryBuilders.multiMatchQuery("李", "name");
        //查所有
        MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();


        //模糊匹配
//        FuzzyQueryBuilder fuzzyQueryBuilder = QueryBuilders.fuzzyQuery("name", "李");
//        nativeSearchQueryBuilder.withQuery(fuzzyQueryBuilder);

        //多条件
//        nativeSearchQueryBuilder.withQuery(QueryBuilders.boolQuery()
//                        .must(QueryBuilders.rangeQuery("age").gte(1).lte(100))
//                .must(fuzzyQueryBuilder)
//                .mustNot(QueryBuilders.matchQuery("age", 12))
//        );

        //前缀查询
        QueryBuilders.prefixQuery("age", "1");
        //通配符查询
        QueryBuilders.wildcardQuery("name", "李*");

        //聚合查询，桶和指标
        //桶-相当于sql的group by
        //指标-字段统计函数
        TermsAggregationBuilder termsAggregationBuilder = AggregationBuilders.terms("genders").field("gender.keyword");
        nativeSearchQueryBuilder.addAggregation(termsAggregationBuilder);

        //取年龄最大
//        MaxAggregationBuilder maxAggregationBuilder = AggregationBuilders.max("age_max").field("age");
//        nativeSearchQueryBuilder.addAggregation(maxAggregationBuilder);

        //求年龄和
//        SumAggregationBuilder sumAggregationBuilder = AggregationBuilders.sum("age_sum").field("age");
//        nativeSearchQueryBuilder.addAggregation(sumAggregationBuilder);

        //求年龄平均
//        AvgAggregationBuilder avgAggregationBuilder = AggregationBuilders.avg("age_avg").field("age");
//        nativeSearchQueryBuilder.addAggregation(avgAggregationBuilder);

        //分页
        Pageable pageable = PageRequest.of(0, 15);
        nativeSearchQueryBuilder.withPageable(pageable);
        //排序
        nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("age").order(SortOrder.DESC));

        nativeSearchQueryBuilder.withQuery(matchAllQueryBuilder);
        NativeSearchQuery nativeSearchQuery = nativeSearchQueryBuilder.build();
        SearchHits<ElsasticUser> userSearchHits = elasticsearchRestTemplate.search(nativeSearchQuery, ElsasticUser.class);
        userSearchHits.get().forEach(u -> {
            System.out.println(u.getContent());
        });

        //取到聚合后的桶数据
        //根据性别分组
        Map<String, Aggregation> stringAggregationMap = userSearchHits.getAggregations().asMap();
        Aggregation genders = stringAggregationMap.get("genders");
        for (int i = 0; i < ((Terms) genders).getBuckets().size(); i++) {
            System.out.println("genders--" + ((Terms) genders).getBuckets().get(i).getKeyAsString() + ":" + ((Terms) genders).getBuckets().get(i).getDocCount());
        }

        //取年龄最大
//        Aggregation ageMax = stringAggregationMap.get("age_max");
//        System.out.println("max--" + ageMax.getType() + ":" + ((ParsedMax) ageMax).getValue());

        //取年龄总和
//        Aggregation ageSum = stringAggregationMap.get("age_sum");
//        System.out.println("sum--" + ageSum.getType() + ":" + ((ParsedSum) ageSum).getValue());

        //取年龄平均
//        Aggregation ageAvg = stringAggregationMap.get("age_avg");
//        System.out.println("avg--" + ageAvg.getType() + ":" + ((ParsedAvg) ageAvg).getValue());
    }

}
