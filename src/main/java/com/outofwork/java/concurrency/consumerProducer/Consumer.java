package com.outofwork.java.concurrency.consumerProducer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author outofwork
 * created on 14/02/21
 */
public class Consumer implements Runnable {

    private final Lock lock;
    private final Condition addData;
    private final Condition removeData;
    private final CountDownLatch countDownLatch;
    private final BlockingQueue<Integer> blockingQueue;

    public Consumer(Lock lock, Condition addData, Condition removeData, CountDownLatch countDownLatch, BlockingQueue<Integer> blockingQueue) {
        this.lock = lock;
        this.addData = addData;
        this.removeData = removeData;
        this.countDownLatch = countDownLatch;
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        try {
            consumer();
        } catch (Exception e) {
            e.printStackTrace();
        }
        countDownLatch.countDown();
    }

    public void consumer() {
        try {
            while (true) {
                Integer value = blockingQueue.poll(15, TimeUnit.SECONDS);
                if (value == null) {
                    break;
                }
                ConsumerProducer.count.decrementAndGet();
                System.out.println("Removing from Queue: " + value);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
