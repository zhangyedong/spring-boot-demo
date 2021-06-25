package com.example.demo;

import com.DemoApplication;
import com.example.book.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.BinaryOperator;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * jdk8
 * zhangyd
 * 2020/4/13 17:12
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DemoApplication.class})
public class Jdk8Test {

    @Test
    public void test() throws IOException {
        //-------------------------------------- 一、基础知识 -------------------------------------------------
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
        userList.sort(Comparator.comparing(User::getId).reversed()
                //id相同时，按名称进一步排序
                .thenComparing(User::getName));

        //id大于10
        Predicate<User> idMore10User = u -> u.getId() > 10;
        //id不是大于10的
        Predicate<User> noIdMore10User = idMore10User.negate();
        idMore10User.and(u -> u.getPhoneNum().startsWith("185")).or(u -> u.getName().equals("lisi"));

        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
        //复合函数先加1后乘2
        Function<Integer, Integer> r = f.andThen(g);
        //复合函数先乘2后加1
        Function<Integer, Integer> r2 = f.compose(g);

        //取较大较小值
        BinaryOperator<Integer> bo = BinaryOperator.maxBy(Comparator.naturalOrder());
        bo.apply(3,4);
        BinaryOperator<Integer> bo2 = BinaryOperator.minBy(Comparator.naturalOrder());
        bo2.apply(3,4);

        //-------------------------------------- 二、函数式数据处理 -------------------------------------------------

        //筛选和匹配
        userList.stream().filter(u -> u.getId() >10)
                        .sorted(Comparator.comparing(User::getName))
                        .distinct()
                        .map(User::getName)
                        .limit(3) //取前三
                        .skip(3)//扔掉前三
                        .collect(Collectors.toList());

        List<Integer> length = userList.stream().map(u -> u.getName().length()).collect(Collectors.toList());

        //映射 flatMap
        Stream<String> stringStream = Arrays.stream(new String[]{"goodbyte","world"});
        List<String> s = stringStream.map(w -> w.split(""))
                .flatMap(Arrays::stream)//把一个流中的每个值都换成另外一种流，然后把所有流连接起来成为一个流
                .distinct()
                .collect(Collectors.toList());
        System.out.println("----jdk8 flatMap-----"+s);

        Stream<Integer> i = Arrays.stream(new Integer[]{1,2,3,4,5});
        System.out.println(i.map(num -> num*num).collect(Collectors.toList()));

        //查找和匹配 allMatch、 anyMatch、 noneMatch、 findFirst findAny
        userList.stream().anyMatch(u -> u.getId() == 10); //是否有id=10的
        Optional<User> o =  userList.stream().filter(u -> u.getId()>10)
                .findAny();
        System.out.println(o);

        Stream<Integer> i2 = Arrays.stream(new Integer[]{1,2,3,4,5});
        i2.filter(num -> num % 2 ==0 )
                .findFirst()
                .ifPresent(System.out::println);

        //归约 reduce
        Stream<Integer> i3 = Arrays.stream(new Integer[]{1,2,3,4,5});
        System.out.println(i3.reduce(0,(a,b) -> a+b));
//        System.out.println(i3.reduce(0,Integer::sum)); //求和
//        System.out.println(i3.reduce(Integer::max)); //最大
//        System.out.println(i3.reduce(Integer::min)); //最小

        //数值流
        userList.stream()
                .mapToLong(User::getId).sum();
        userList.stream().mapToLong(User::getId).average();
        Stream<Long> boxed = userList.stream().mapToLong(User::getId).boxed();

        //数值范围
        IntStream.rangeClosed(1,100).filter(n -> n%2 == 0).count(); //50
        IntStream.range(1,100).filter(n -> n%2 == 0).count(); //49 不包含结束值

        //创建流
        Stream.of("Java","Python");
        Stream.empty();
        Arrays.stream(new Integer[]{1,2,3});

        Path path = Paths.get("c:/user.txt");
        Files.lines(path, Charset.forName("UTF-8")).forEach(System.out::println); //一行一行的读

        Stream.generate(Math::random).limit(5).forEach(System.out::println);

        CompletableFuture<BigDecimal> results = CompletableFuture.completedFuture(new BigDecimal(BigInteger.ZERO));

        //组合式异步编程 CompletableFuture
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> "start");

        CompletableFuture.supplyAsync(() -> "start")
                .thenApply(str -> str.replace("s","t"))
                .thenCompose(str -> CompletableFuture.supplyAsync(() -> str.substring(1)))
                .thenCombine(CompletableFuture.supplyAsync(() -> "a"),(a,b) -> a + b)
                //异常处理
                .exceptionally(e -> {
                    System.out.println(e.getMessage());
                    return "异常返回";
                }).thenAccept(System.out::println);
        try {
            stringCompletableFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDate localDate = LocalDate.now();
        LocalDate date1 = LocalDate.of(2014, 3, 18);
        LocalDate date2 = date1.withYear(2011);
        LocalDate date3 = date2.withDayOfMonth(25);
        LocalDate date4 = date3.with(ChronoField.MONTH_OF_YEAR, 9);

        LocalDate date5 = date1.plusWeeks(1);
        LocalDate date6 = date5.minusYears(3);
        LocalDate date7 = date6.plus(6, ChronoUnit.MONTHS);

        TemporalAdjusters.lastDayOfMonth();
        //同一个月中每一周的第几天
        TemporalAdjusters.dayOfWeekInMonth(1,DayOfWeek.from(date1));

        //1-10阶乘
        long n = 10;
        LongStream.rangeClosed(1,n).reduce(1,(a,b) -> a * b);

        Function<User,Boolean> function = Jdk8Test::isMoreTwo;

        //科里化
        DoubleUnaryOperator convertCtoF = curriedConverter(9.0/5, 32);
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

    static DoubleUnaryOperator curriedConverter(double f, double b){
        return (double x) -> x * f + b;
    }
}
