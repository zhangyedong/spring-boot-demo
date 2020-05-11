package com.example.demo;

import com.DemoApplication;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.mapper.UserPlusMapper;
import com.example.demo.mapper.UserPlusXmlMapper;
import com.example.demo.model.User;
import com.example.demo.service.UserPlusService;
import com.example.demo.service.UserPlusXmlService;
import com.example.demo.service.UserService;
import com.example.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
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

//    @Autowired
//    RedisTemplate redisTemplate;

    @Autowired
    UserPlusService userPlusService;

    @Autowired
    UserPlusMapper userPlusMapper;

    @Autowired
    UserPlusXmlService userPlusXmlService;

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
//        redisTemplate.opsForValue().set("my_name","张业东",100L, TimeUnit.SECONDS);
//        log.info("--------logback------ redis-value:{}",redisTemplate.opsForValue().get("my_name"));
//        Assert.assertEquals("张业东1",redisTemplate.opsForValue().get("my_name"));



        //--------------mybatis-plus--------------start
        List<User> userList = userPlusService.findUserAll();
//        Assert.assertEquals(3L,userList.size());
//        userList.forEach(System.out::println);
        //allEq
//        Map<SFunction<User,?>,Object> map = new HashMap<>();
//        map.put(User::getName,"zhangsan");
//        List<User> userList = userPlusMapper.selectList(Wrappers.<User>lambdaQuery().allEq(map));
        log.info("--------------mybatis-plus--------------");
        //eq
//        userPlusMapper.selectList(Wrappers.<User>lambdaQuery().eq(User::getName,"zhangsan").eq(User::getPhoneNum,"18375893885"));
        //ne 不等于
//        userList = userPlusMapper.selectList(Wrappers.<User>lambdaQuery().ne(User::getName,"zhangsan"));
        //gt 大于
//        userList = userPlusMapper.selectList(Wrappers.<User>lambdaQuery().gt(User::getId,1));
        //lt 小于
//        userList = userPlusMapper.selectList(Wrappers.<User>lambdaQuery().lt(User::getId,3));
        //le 小于等于
//        userList = userPlusMapper.selectList(Wrappers.<User>lambdaQuery().le(User::getId,2));
        //between 范围
//        userList = userPlusMapper.selectList(Wrappers.<User>lambdaQuery().between(User::getId,2,3));
        //notBetween 范围取反
//        userList = userPlusMapper.selectList(Wrappers.<User>lambdaQuery().notBetween(User::getId,2,3));
        //like 模糊匹配
//        userList = userPlusMapper.selectList(Wrappers.<User>lambdaQuery().like(User::getName,"z"));
        //notLike 模糊匹配取反
//        userList = userPlusMapper.selectList(Wrappers.<User>lambdaQuery().notLike(User::getName,"z"));
        //likeLeft %值
//        userList = userPlusMapper.selectList(Wrappers.<User>lambdaQuery().likeLeft(User::getName,"z"));
        //likeRight 值%
//        userList = userPlusMapper.selectList(Wrappers.<User>lambdaQuery().likeRight(User::getName,"z"));
        //isNull
//        userList = userPlusMapper.selectList(Wrappers.<User>lambdaQuery().isNull(User::getName));
        //isNotNull
//        userList = userPlusMapper.selectList(Wrappers.<User>lambdaQuery().isNotNull(User::getName));
        //in
//        Set<Integer> inSet = new HashSet<>();
//        inSet.add(1);
//        inSet.add(3);
//        userList = userPlusMapper.selectList(Wrappers.<User>lambdaQuery().in(User::getId,inSet));
        //notIn
//        userList = userPlusMapper.selectList(Wrappers.<User>lambdaQuery().notIn(User::getId,inSet));
        //inSql
//        userList = userPlusMapper.selectList(Wrappers.<User>lambdaQuery().inSql(User::getId,"select id from user where id = 2"));
        //notInSql
//        userList = userPlusMapper.selectList(Wrappers.<User>lambdaQuery().notInSql(User::getId,"select id from user where id = 2"));
        //groupBy
//        userList = userPlusMapper.selectList(Wrappers.<User>lambdaQuery().groupBy(User::getId,User::getName));
        //orderByAsc
//        userList = userPlusMapper.selectList(Wrappers.<User>lambdaQuery().orderByAsc(User::getId,User::getName));
        //orderByDesc
//        userList = userPlusMapper.selectList(Wrappers.<User>lambdaQuery().orderByDesc(User::getId,User::getName));
        //orderBy
//        userList = userPlusMapper.selectList(Wrappers.<User>lambdaQuery().orderBy(true,true,User::getId,User::getName));
        //having
//        userList = userPlusMapper.selectList(Wrappers.<User>lambdaQuery().groupBy(User::getId).having("sum(id) > 3"));
        //or
//        userList = userPlusMapper.selectList(Wrappers.<User>lambdaQuery().eq(User::getId,1).eq(User::getName,"zhangsan").or().eq(User::getId,2));
        //and
//        userList = userPlusMapper.selectList(Wrappers.<User>lambdaQuery()
//                .and(u -> u.eq(User::getId,1)
//                        .ne(User::getName,"lisi"))
//                .and(u -> u.eq(User::getPhoneNum,"18375893885")));
        //nested nested(i -> i.eq("name", "李白").ne("status", "活着"))--->(name = '李白' and status <> '活着')
//                userList = userPlusMapper.selectList(Wrappers.<User>lambdaQuery()
//                .nested(u -> u.eq(User::getId,1)
//                        .ne(User::getName,"lisi")));
        //apply 拼接sql
//        userList = userPlusMapper.selectList(Wrappers.<User>lambdaQuery().apply("and id > 2"));
        //exists
        //notExists

        //自定义sql
//        userList = userPlusMapper.selectAllByName("zhangsan");
//        userList.forEach(System.out::println);
        //分页插件
        IPage<User> iPage = userPlusXmlService.findPageByName(new Page<>(1L,5L),"zhangsan");
        iPage.getRecords().forEach(System.out::println);

        //--------------mybatis-plus--------------end

    }



}
