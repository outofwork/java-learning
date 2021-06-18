package com.outofwork.java.concurrency;

import java.util.concurrent.CountDownLatch;

/**
 * @author outofwork
 * created on 14/02/21
 */
public class LatchDependantService implements Runnable {

    private final CountDownLatch countDownLatch;
    private final int number;

    public LatchDependantService(CountDownLatch countDownLatch, int number) {
        this.countDownLatch = countDownLatch;
        this.number = number;
    }

    @Override
    public void run() {
        System.out.println("Running Here");
        try {
            Thread.sleep(number * 2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Running Completed for " + number);
        countDownLatch.countDown();
    }
}
