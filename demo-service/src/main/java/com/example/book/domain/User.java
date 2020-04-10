package com.example.book.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * user
 * zhangyd
 * 2020/3/29 18:29
 */
@Data
@NamedQuery(name = "User.withBookNameAndPasswordQuery",
        query = "select u from User u where u.name = ?1 and u.password = ?2")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String password;

    private String phoneNum;
}
