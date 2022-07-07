package com.example.demo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;

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
            synchronized (resourceA) {
                System.out.println("threadA get resourceA...");

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("threadA waiting resourceB...");
                synchronized (resourceB) {
                    System.out.println("threadA get resourceB...");
                }
            }
        });

        Thread threadB = new Thread(() -> {
            synchronized (resourceA) {
                System.out.println("threadB get resourceA...");

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("threadB waiting resourceB...");
                synchronized (resourceB) {
                    System.out.println("threadB get resourceB...");
                }
            }
        });
        threadA.start();
        threadB.start();

//        ThreadLocal
        ThreadLocal<String> threadLocal = ThreadLocal.withInitial(() -> "thread");
        threadLocal.set("");
        threadLocal.get();
        threadLocal.remove();

        ThreadLocalRandom localRandom = ThreadLocalRandom.current();
        for (int i = 0; i < 10; i++) {
            localRandom.nextInt(5);
        }

        AtomicLong atomicLong = new AtomicLong(1);
        atomicLong.incrementAndGet();

        LongAdder longAdder = new LongAdder();
        longAdder.increment();
        longAdder.decrement();
        longAdder.sum();
        longAdder.longValue();

        //CopyOnWriteArrayList
        CopyOnWriteArrayList<Integer> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        copyOnWriteArrayList.add(1);
        copyOnWriteArrayList.addIfAbsent(2);

        //LockSupport
        LockSupport.park();
        LockSupport.unpark(threadB);

        //抽象同步队列AQS
        AbstractQueuedSynchronizer abstractQueuedSynchronizer;
        //独占读写锁
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
        ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();

        StampedLock stampedLock = new StampedLock();
        long numRead = stampedLock.readLock();
        long numWrite = stampedLock.writeLock();
        stampedLock.unlockRead(numRead);
        stampedLock.unlockWrite(numWrite);
        stampedLock.tryOptimisticRead();
        //三种锁可以互相转换
        stampedLock.tryConvertToReadLock(numWrite);

        //底层单向链表  cas非阻塞队列
        ConcurrentLinkedQueue<String> linkedQueue = new ConcurrentLinkedQueue<>();
        //队尾追加元素
        linkedQueue.add("1");
        linkedQueue.offer("2");
        //队头取元素
        linkedQueue.element();
        linkedQueue.peek();
        //队头取并删元素
        linkedQueue.remove();
        linkedQueue.poll();

        //独占锁 阻塞队列、生产消费模型
        LinkedBlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<>();
        linkedBlockingQueue.add("1");
        linkedBlockingQueue.offer("2");
        try {
            linkedBlockingQueue.put("3");
            linkedBlockingQueue.take();
            linkedBlockingQueue.poll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //有界数组阻塞队列 、全局独占锁、size统计精准
        ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(10);
        try {
            arrayBlockingQueue.put("1");
            arrayBlockingQueue.offer("2");
            arrayBlockingQueue.add("3");

            arrayBlockingQueue.take();
            arrayBlockingQueue.poll();
            arrayBlockingQueue.peek();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //带优先级的无界阻塞队列、平衡二叉树堆
        PriorityBlockingQueue<String> priorityBlockingQueue = new PriorityBlockingQueue<>();

    }


}
