package com.example.demo;

import com.DemoApplication;
import com.example.demo.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.beans.Beans;

/**
 * spring4.x学习
 * zhangyd
 * 2020/7/4 10:26
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DemoApplication.class})
public class Spring4Test {

    @Test
    public void test(){
        //BeanFactory 介绍
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource res = resolver.getResource("");
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(res);
        User user = factory.getBean(User.class);

        //用hashmap实现的bean缓存器

        ApplicationContext context = new ClassPathXmlApplicationContext("com/...");
        ApplicationContext context1 = new FileSystemXmlApplicationContext("com/...");
        ApplicationContext context2 = new AnnotationConfigApplicationContext(Beans.class);
        user = context2.getBean("user",User.class);
        Assert.assertNotNull(user);

//        WebApplicationContextUtils.getWebApplicationContext();
    }
}
