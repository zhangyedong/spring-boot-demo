package com.example.demo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 并发编程之美
 * zhangyd
 */
@Slf4j
public class ConcurrentBeautifulTest {

    private static final Object resourceA = new Object();
    private static final Object resourceB = new Object();

    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) {
//        log.info("enter...");
//        try {
//            FutureTask<Integer> futureTask = new FutureTask<>(() -> 1 + 2);
//            new Thread(futureTask).start();
//            log.info(futureTask.get() + "");
//        } catch (InterruptedException | ExecutionException e) {
//            e.printStackTrace();
//        }


//        Thread threadOne = new Thread(() -> {
//            System.out.println("threadOne begin run!");
//            for (;;){}
//        });
//
//        final Thread currentThread = Thread.currentThread();
//
//        Thread threadTwo = new Thread(() -> {
//            System.out.println("threadTwo begin run!");
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            //中断主线程
//            currentThread.interrupt();
//        });
//
//        threadOne.start();
//        threadTwo.start();
//
//        //等待one执行完成,会抛出异常
//        try {
//            threadOne.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


        //线程中断
//        Thread threadA = new Thread(() ->{
//            lock.lock();
//            try {
//                System.out.println("threadA is in sleep...");
//                Thread.sleep(10000);
//                System.out.println("threadA is  awaked...");
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }finally {
//                lock.unlock();
//            }
//        });
//
//        Thread threadB = new Thread(() ->{
//            lock.lock();
//            try {
//                System.out.println("threadB is in sleep...");
//                Thread.sleep(10000);
//
//                System.out.println("threadB is  awaked...");
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }finally {
//                lock.unlock();
//            }
//        });
//
//        threadA.start();
//        threadB.start();
//        threadA.interrupt();


        //死锁例子-因互相等待对方资源
//        Thread threadA = new Thread(() -> {
//            synchronized (resourceA){
//                System.out.println("threadA get resourceA...");
//
//                try{
//                    Thread.sleep(5000);
//                }catch(InterruptedException e){
//                    e.printStackTrace();
//                }
//
//                System.out.println("threadA waiting resourceB...");
//                synchronized (resourceB){
//                    System.out.println("threadA get resourceB...");
//                }
//            }
//        });
//
//        Thread threadB = new Thread(() -> {
//            synchronized (resourceB){
//                System.out.println("threadB get resourceB...");
//
//                try{
//                    Thread.sleep(5000);
//                }catch(InterruptedException e){
//                    e.printStackTrace();
//                }
//
//                System.out.println("threadB waiting resourceA...");
//                synchronized (resourceA){
//                    System.out.println("threadB get resourceA...");
//                }
//            }
//        });
//        threadA.start();
//        threadB.start();
//    }

    //死锁例子-因互相等待对方资源（解决死锁）,保证申请资源的有序性可解决
    Thread threadA = new Thread(() -> {
        synchronized (resourceA){
            System.out.println("threadA get resourceA...");

            try{
                Thread.sleep(5000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }

            System.out.println("threadA waiting resourceB...");
            synchronized (resourceB){
                System.out.println("threadA get resourceB...");
            }
        }
    });

    Thread threadB = new Thread(() -> {
        synchronized (resourceA){
            System.out.println("threadB get resourceA...");

            try{
                Thread.sleep(5000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }

            System.out.println("threadB waiting resourceB...");
            synchronized (resourceB){
                System.out.println("threadB get resourceB...");
            }
        }
    });
        threadA.start();
        threadB.start();
}


}
