package com.example.book.controller;

import com.example.book.aop.DemoAnnotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * TODO
 * zhangyd
 * 2020/4/1 11:19
 */
@Controller
public class BookController {
    
    @Value("${book.author}")
    private String author;

    @Autowired
    DemoAnnotationService demoAnnotationService;

//    @GetMapping(value = "/bookTest",produces = {"application/json","charset=UTF-8"})
    @GetMapping(value = "/bookTest")
    public String bookTest() {
//        demoAnnotationService.add();
        System.out.println("-----author---"+author);
        return "index";
    }
}
