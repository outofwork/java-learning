package com.outofwork.java.concurrency;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author outofwork
 * created on 14/02/21
 */
public class CountDownLatchService {

    public static CountDownLatch countDownLatch = new CountDownLatch(3);

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        executorService.submit(new LatchDependantService(countDownLatch, 1));
        executorService.submit(new LatchDependantService(countDownLatch, 2));
        executorService.submit(new LatchDependantService(countDownLatch, 3));
        //countDownLatch.await();

        System.out.println("All Service Completed");

    }
}

