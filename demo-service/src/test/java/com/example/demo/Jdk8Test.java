package com.example.demo;

import com.DemoApplication;
import com.example.book.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * jdk8
 * zhangyd
 * 2020/4/13 17:12
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DemoApplication.class})
public class Jdk8Test {

    @Test
    public void test() {
        List<User> userList = new ArrayList<>();
        File[] hiddenFiles = new File(".").listFiles(File::isHidden);
        filterUsers(userList, Jdk8Test::isMoreTwo);
        filterUsers(userList, Jdk8Test::isMoreThree);
        filterUsers(userList, u -> u.getId() > 2 || u.getId() > 3);

        userList.stream()
                .filter(u -> u.getId() > 2)
                .collect(Collectors.toList());
        //并行
        userList.parallelStream().filter(u -> u.getId() > 2).collect(Collectors.toList());

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
        thread = new Thread(() -> System.out.println("111"));
//        Optional<String> optional = Optional.empty();
//        System.out.println("-----optional----" + optional);
//        Optional.ofNullable(new User()).filter(u -> u.getId() > 2).ifPresent(u -> System.out.println(u.getName()));
//        Optional<Long> num = Optional.ofNullable(new User()).map(User::getId);
//        Long num1 = Optional.ofNullable(new User()).map(User::getId).orElse(0L);


        Comparator<User> comparator = Comparator.comparing(User::getId);
//        Predicate 断言
//        Consumer 消费
//        Function 功能
//        Supplier 提供

        //方法引用 lambda的语法糖，他的另外一种写法
        userList.sort(Comparator.comparing(User::getId));
    }

    public static <T> List<T> filterUsers(List<T> userList, Predicate<T> p) {
        List<T> result = new ArrayList<>();
        for (T t : userList) {
            if (p.test(t)) {
                result.add(t);
            }
        }
        return result;
    }

    public static boolean isMoreTwo(User user) {
        return user.getId() > 2;
    }

    public static boolean isMoreThree(User user) {
        return user.getId() > 3;
    }
}
