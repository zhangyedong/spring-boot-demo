package com.example.book.executor;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * TODO
 * zhangyd
 * 2020/4/1 15:23
 */
@Service
public class AsyncTaskService {

    @Async
    public void executeAsyncTask(Integer num){
        System.out.println("执行异步任务1====="+num);
    }

    @Async
    public void executeAsyncTaskPlus(Integer num){
        System.out.println("执行异步任务2====="+(num+1));
    }
}
