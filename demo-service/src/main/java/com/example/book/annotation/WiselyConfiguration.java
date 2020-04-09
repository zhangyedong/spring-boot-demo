package com.example.book.annotation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@ComponentScan
@Configuration
@Retention(RetentionPolicy.RUNTIME)
public @interface WiselyConfiguration {
    String[] value() default {};
}
