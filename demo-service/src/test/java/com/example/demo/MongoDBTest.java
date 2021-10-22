package com.example.demo;

import com.DemoApplication;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.model.Person;
import com.mongodb.client.model.Collation;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.mongodb.core.FindAndReplaceOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOptions;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.BasicUpdate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.schema.MongoJsonSchema;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * MongoDBTest
 * zhangyd
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DemoApplication.class})
public class MongoDBTest {

    private final MongoTemplate mongoTemplate;

    MongoDBTest(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Test
    public void test() {
        Person person = new Person();
        person.setId("1");
        person.setName("zhangyd");
        person.setAge(27);
        mongoTemplate.save(person, "zhangyd");

        Person person1 = mongoTemplate.findOne(Query.query(Criteria.where("id").is(1)), Person.class);

        mongoTemplate.query(Person.class)
                .matching(Query.query(Criteria.where("id").lte(1)
                        .and("name").is("zhangyd")));
        mongoTemplate.findById("1", Person.class);
        mongoTemplate.query(Person.class).distinct("name");


        mongoTemplate.updateFirst(Query.query(Criteria.where("id").is(1)),
                BasicUpdate.update("name", "zhangyd2"), Person.class);

        mongoTemplate.updateMulti(Query.query(Criteria.where("id").is(1)),
                BasicUpdate.update("name", "zhangyd2"), Person.class);

        Collation collation = Collation.builder().build();
        AggregationOptions build = AggregationOptions.builder().collation(null).build();

        mongoTemplate.updateFirst(Query.query(Criteria.where("id").is(1)),
                BasicUpdate.update("name", "zhangyd2")
                        .addToSet("age").value(18)
                        .currentDate("birthday")
                        .currentTimestamp("birthday")
                        .inc("age", Integer.parseInt("1"))
                        .max("age", 30)
                        .min("age", 10)
                        .multiply("age", 10)
                        .rename("name", "name666")
                        .unset("age")
                , Person.class);

        mongoTemplate.update(Person.class)
                .matching(Query.query(Criteria.where("id").is(1).and("name").is("zhangyd")))
                .apply(Update.update("name", "zhangyd2"))
                .upsert();

        mongoTemplate.findAndModify(Query.query(Criteria.where("id").is(1)),
                Update.update("name", "zhengsan"), Person.class);

        mongoTemplate.update(Person.class)
                .matching(Query.query(Criteria.where("id").is(1)))
                .replaceWith(person)
                .withOptions(FindAndReplaceOptions.options().returnNew());

        mongoTemplate.remove(Query.query(Criteria.where("id").is(1)));
        mongoTemplate.remove(Query.query(Criteria.where("id").is(1)), "zhangyd");
        mongoTemplate.findAndRemove(Query.query(Criteria.where("id").is(1)), Person.class);


        //多条件and
        Criteria criteria = new Criteria();
        criteria.andOperator(Criteria.where("age").lt(10),Criteria.where("name").regex("zhangyd"));

        //多条件or
        criteria.orOperator(Criteria.where("age").lt(10),Criteria.where("name").regex("zhangyd"));
        Query.query(criteria);

        //聚合
        Aggregation aggregation = Aggregation.newAggregation(
                //条件
                Aggregation.match(Criteria.where("name").regex("zhang")),
                //根据年龄聚合
                Aggregation.group("age").count().as("age_count"),
                //重命名指标名称
                Aggregation.project("age_count").and("age").previousOperation()
        );

        AggregationResults<JSONObject> aggregate = mongoTemplate.aggregate(aggregation, mongoTemplate.getCollectionName(Person.class), JSONObject.class);
        aggregate.getMappedResults().forEach(j -> {
            Integer age = j.getInteger("age");
            Long ageCount = j.getLong("age_count");
            log.info("年龄：{}，数量：{}", age, ageCount);
        });

    }

}
