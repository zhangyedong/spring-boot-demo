package com.example.demo;

import com.DemoApplication;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * RocketMQTest
 * zhangyd
 * 2021/1/30 16:36
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DemoApplication.class})
public class RocketMQTest {

    @Test
    public void test(){
        DefaultMQProducer producer = new DefaultMQProducer("please rename unique group name ");
        producer.setNamesrvAddr("192.168.100.131:9876");

        try {
            producer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }


    }

}
