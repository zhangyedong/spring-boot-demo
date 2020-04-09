package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * TODO
 * zhangyd
 * 2020/3/29 17:52
 */
@Controller
public class HelloController {

    @GetMapping("/index")
    public String index() {
        return "index";
    }
}
