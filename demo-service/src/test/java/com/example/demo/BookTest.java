package com.example.demo;

import com.DemoApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author: zhangyd
 * @date: 2019/10/22 9:52
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DemoApplication.class})
public class BookTest {

//    @Autowired
//    UserService userService;
//
//    @Autowired
//    UserRepository userRepository;

    @Autowired
    RedisTemplate redisTemplate;

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
//        System.out.println("-------findAllByName-------"+ userRepository.findAllByName("zhangsan"));
//        System.out.println("-------withNameAndPasswordQuery-------"+ userRepository.withNameAndPasswordQuery("zhangsan","456"));
//        User user = new User();
//        user.setName("zhaoliu");
//        user.setPassword("66666");
//        user.setPhoneNum("18666666666");
//        System.out.println("-------save-------"+userBookRepository.save(user));
//        System.out.println("-------findAll-------"+ userRepository.findAll());

        //spring事务 PlatformTransactionManager接口
//        声明式事务 @Transactional()
        //事务的传播行为  事务的隔离级别 mysql默认隔离级别为可重复读

        //序列化和反序列化
//        try {
//            @Cleanup ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("C:\\userObj.txt"));
//            User user = new User();
//            user.setId(55);
//            user.setName("zhangyd");
//            user.setPhoneNum("18375893885");
//            user.setPassword("0920");
//            outputStream.writeObject(user);
//
//            @Cleanup ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("C:\\userObj.txt"));
//            User u = (User)inputStream.readObject();
//            System.out.println(u);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        //redis string
        redisTemplate.opsForValue().set("my_name","张业东",100L, TimeUnit.SECONDS);
        log.info("--------logback------ redis-value:{}",redisTemplate.opsForValue().get("my_name"));
    }



}
