package com.example.demo;

import com.DemoApplication;
import com.example.book.domain.User;
import com.example.book.repository.UserRepository;
import com.example.demo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * TODO
 *
 * @author: zhangyd
 * @date: 2019/10/22 9:52
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DemoApplication.class})
public class BookTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Test
    public void bookTest(){
        //事件 ApplicationEvent
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(EventConfig.class);
//        DemoPublisher publisher = context.getBean(DemoPublisher.class);
//        publisher.publish("发送的信息。。。");

//        //aop
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AopConfig.class);
//        DemoAnnotationService demoAnnotationService = context.getBean(DemoAnnotationService.class);
//        demoAnnotationService.add();

        //多线程任务执行
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TaskExecutorConfig.class);
//        AsyncTaskService service = context.getBean(AsyncTaskService.class);
//        for (int i = 0; i < 10; i++) {
//            service.executeAsyncTask(i);
//            service.executeAsyncTaskPlus(i);
//        }

        //定时任务
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ScheduledConfig.class);
//        try {
//            Thread.sleep(1000*20);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        //组合元注解
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
//        DemoService service = context.getBean(DemoService.class);
//        service.out();


//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
//        context.close();

        //spring-data jpa

        System.out.println("-------findAllByName-------"+userRepository.findAllByName("zhangsan"));
        System.out.println("-------withNameAndPasswordQuery-------"+userRepository.withNameAndPasswordQuery("zhangsan","456"));
        User user = new User();
        user.setName("zhaoliu");
        user.setPassword("66666");
        user.setPhoneNum("18666666666");
        System.out.println("-------save-------"+userRepository.save(user));
        System.out.println("-------findAll-------"+userRepository.findAll());


    }

}
