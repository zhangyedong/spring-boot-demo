package com.example.book.batch;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

/**
 * job监听
 * zhangyd
 * 2020/4/13 11:23
 */
public class CsvJobListener implements JobExecutionListener {

    long startTime;
    long endTime;

    @Override
    public void beforeJob(JobExecution jobExecution) {
        startTime = System.currentTimeMillis();
        System.out.println("------batch任务开始处理------");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        endTime = System.currentTimeMillis();
        System.out.println("------batch任务处理结束------");
        System.out.println("------耗时------" + (endTime - startTime) + "ms");
    }
}
