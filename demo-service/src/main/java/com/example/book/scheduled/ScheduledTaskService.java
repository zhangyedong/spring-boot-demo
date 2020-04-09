package com.example.book.scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * TODO
 * zhangyd
 * 2020/4/1 15:44
 */
@Service
public class ScheduledTaskService {

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime(){
        System.out.println("每隔五秒执行一次:"+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }

    /**
     * 指定时间点执行
     *
     */
    @Scheduled(cron = "0 25 16 ? * *")
    public void fixTimeExecution(){
        System.out.println("在指定时间执行："+LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }
}
