package com.example.demo;

import com.DemoApplication;
import com.example.book.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.ArrayList;
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


    }

    public static List<User> filterUsers(List<User> userList, Predicate<User> p) {
        List<User> result = new ArrayList<>();
        for (User user : userList) {
            if (p.test(user)) {
                result.add(user);
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
