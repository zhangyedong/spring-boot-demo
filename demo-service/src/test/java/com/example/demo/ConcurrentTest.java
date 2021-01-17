package com.example.demo;

import com.DemoApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.applet.Main;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 实战java高并发程序设计
 * zhangyd
 * 2020/7/20 16:34
 */
//@Slf4j
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {DemoApplication.class})
public class ConcurrentTest {

    @Test
    public void test(){
        Runnable runnable = () -> System.out.println("线程开始执行----");
        Thread thread = new Thread(runnable);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
        thread.setName("");


        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.lock();
        reentrantLock.unlock();
        reentrantLock.tryLock();
        Condition condition = reentrantLock.newCondition();
        try {
            condition.await();
            condition.signal();
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Semaphore semaphore = new Semaphore(2);
        Semaphore semaphore2 = new Semaphore(2,true);
        try {
            semaphore.acquire();
            semaphore.acquireUninterruptibly();
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        Lock writeLock = readWriteLock.writeLock();
        readWriteLock.readLock();
        readWriteLock.writeLock();

        CountDownLatch countDownLatch = new CountDownLatch(5);
        countDownLatch.countDown();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        countDownLatch.getCount();

        CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
        try {
            cyclicBarrier.await();

        } catch (Exception e) {
            e.printStackTrace();
        }

        LockSupport.park();
        LockSupport.parkNanos(1000);
        LockSupport.parkUntil(1000);
        LockSupport.unpark(new Thread());


        ExecutorService executor = new ThreadPoolExecutor(2,2,5, TimeUnit.SECONDS,new LinkedBlockingQueue<>());
        Executors.newFixedThreadPool(5);
        Executors.newSingleThreadExecutor();

    }


    public static void main(String[] args){
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        final SemapDemo semapDemo = new SemapDemo();
        for (int i = 0; i < 20; i++) {
            executorService.submit(semapDemo);
        }
    }

}
