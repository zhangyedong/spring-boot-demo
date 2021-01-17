package com.example.demo;

import java.util.concurrent.Semaphore;

public class SemapDemo implements Runnable{
    final Semaphore semaphore = new Semaphore(5);
    @Override
    public void run() {
        try {
            semaphore.acquire();
            Thread.sleep(2000);
            System.out.println("-----"+Thread.currentThread().getId()+"done!!!");
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
