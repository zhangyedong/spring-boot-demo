package com.example.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * TODO
 * zhangyd
 * 2020/4/18 13:16
 */
public class Test {
    public static void main(String[] args) {
//        Callable<String> callable = () -> Thread.currentThread().getName();
//        FutureTask<String> futureTask = new FutureTask<>(callable);
//        new Thread(futureTask).start();
//
//        try {
//            System.out.println(futureTask.get());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }

//        int corePoolSize,
//        int maximumPoolSize,
//        long keepAliveTime,
//        TimeUnit unit,
//        BlockingQueue<Runnable> workQueue
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(5,5,
//                5L,TimeUnit.SECONDS,new LinkedBlockingQueue<>(5));
//        CountDownLatch countDownLatch = new CountDownLatch(1);
//        try {
//            countDownLatch.await();
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        ThreadPoolExecutor.AbortPolicy abortPolicy = new ThreadPoolExecutor.AbortPolicy();
//        ThreadPoolExecutor.CallerRunsPolicy callerRunsPolicy = new ThreadPoolExecutor.CallerRunsPolicy();
//        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(5);
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 2,
//                0L, TimeUnit.SECONDS, queue);
//        executor.execute(()->{
//
//        });

        //信号量
//        Semaphore semaphore = new Semaphore(5);
//        try{
//            semaphore.acquire();
//            //业务代码
//        }catch(Exception e){
//            semaphore.release();
//        }

//        AtomicInteger atomicInteger = new AtomicInteger(0);
//        int a = atomicInteger.incrementAndGet();
//        System.out.println(a);

        //可重入锁、递归锁
//        ReentrantLock reentrantLock = new ReentrantLock();
//        reentrantLock.lock();
//        reentrantLock.unlock();
//
//        //读写锁
//        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
//        Lock readLock = readWriteLock.readLock();
//        Lock writeLock = readWriteLock.writeLock();


//        int N = 8; //工人数
//        Semaphore semaphore = new Semaphore(5); //机器数目
//        for(int i=0;i<N;i++)
//            new Worker(i,semaphore).start();


    }

//    static class Worker extends Thread{
//        private int num;
//        private Semaphore semaphore;
//        public Worker(int num,Semaphore semaphore){
//            this.num = num;
//            this.semaphore = semaphore;
//        }

    public static void copyFileUseNIO(String src, String dst) throws IOException {
//声明源文件和目标文件
        FileInputStream fi = new FileInputStream(new File(src));
        FileOutputStream fo = new FileOutputStream(new File(dst));
        //获得传输通道channel
        FileChannel inChannel = fi.getChannel();
        FileChannel outChannel = fo.getChannel();
        //获得容器buffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (true) {
            //判断是否读完文件
            int eof = inChannel.read(buffer);
            if (eof == -1) {
                break;
            }
            //重设一下buffer的position=0，limit=position
            buffer.flip();
            //开始写
            outChannel.write(buffer);
            //写完要重置buffer，重设position=0,limit=capacity
            buffer.clear();
        }
        inChannel.close();
        outChannel.close();
        fi.close();
        fo.close();
    }

}


