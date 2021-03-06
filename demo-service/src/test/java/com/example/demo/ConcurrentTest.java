package com.example.demo;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;


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


        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 2, 5,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>(), Executors.defaultThreadFactory(), new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println(r.toString()+"拒绝执行...");
            }
        });
        Executors.newFixedThreadPool(5);
        Executors.newSingleThreadExecutor();
        Executors.newCachedThreadPool();
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        ScheduledExecutorService scheduledExecutorService1 = Executors.newScheduledThreadPool(5);
        scheduledExecutorService.scheduleWithFixedDelay(()->{
            try {
                Thread.sleep(5000);
                System.out.println(System.currentTimeMillis()/1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },0,2,TimeUnit.SECONDS);

        executor.execute(()-> System.out.println("执行业务"));

        ThreadFactory threadFactory = new ThreadFactoryBuilder().setDaemon(false).setNameFormat("").build();

        ExecutorService executorService = new ThreadPoolExecutor(5,5,0L,
                TimeUnit.MILLISECONDS,new LinkedBlockingQueue<>()){
            //执行前
            @Override
            protected void beforeExecute(Thread t,Runnable r){
                System.out.println("准备执行");
            }
            //执行后
            @Override
            protected void afterExecute(Runnable r,Throwable t){
                System.out.println("执行完成");
            }
            //线程池退出的时候
            @Override
            protected void terminated(){
                System.out.println("线程池退出");
            }
        };
        executorService.shutdown();
        Runtime.getRuntime().availableProcessors();

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Long> forkJoinTask = forkJoinPool.submit(()->{
            return 0L;
        });
        Collections.synchronizedMap(new HashMap<>());

        ConcurrentLinkedQueue concurrentLinkedQueue = new ConcurrentLinkedQueue<>();
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        copyOnWriteArrayList.add("a");
        copyOnWriteArrayList.add("b");
        copyOnWriteArrayList.get(0);

        BlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
        BlockingQueue arrayBlockingQueue = new ArrayBlockingQueue<>(10);
        arrayBlockingQueue.offer(null);
        try {
            arrayBlockingQueue.put(null);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.incrementAndGet();
        atomicInteger.getAndIncrement();
        atomicInteger.addAndGet(1);

        StampedLock stampedLock = new StampedLock();
        long stamp = stampedLock.tryOptimisticRead();
        stampedLock.unlockWrite(stamp);
        stampedLock.unlockRead(stamp);

        LongAdder longAdder = new LongAdder();
        longAdder.increment();
        longAdder.sum();

        LongAccumulator longAccumulator = new LongAccumulator(Long::max, Long.MIN_VALUE);
        longAccumulator.accumulate(1);
        longAccumulator.get();
        longAccumulator.longValue();

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
    }


    public static void main(String[] args){
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        final SemapDemo semapDemo = new SemapDemo();
        for (int i = 0; i < 20; i++) {
            executorService.submit(semapDemo);
        }
    }

}
