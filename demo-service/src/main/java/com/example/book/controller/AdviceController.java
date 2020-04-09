package com.example.book.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * TODO
 * zhangyd
 * 2020/4/2 15:00
 */
@Controller
public class AdviceController {

    @GetMapping("/advice")
    public String getSomething(@ModelAttribute("msg") String msg) {
        throw new IllegalArgumentException("非常抱歉，参数有误/，来自@ModelAttribute：" + msg);
    }
}
