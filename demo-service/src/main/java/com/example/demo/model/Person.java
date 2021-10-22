package com.example.demo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Person-Mongodb测试
 * zhangyd
 * 2021/2/4 9:34
 */
@Data
@Document
public class Person {
    @Id
    private String id;
    private String name;
    private Integer age;
    private String birthday;

    @Version
    private Long version;
}
