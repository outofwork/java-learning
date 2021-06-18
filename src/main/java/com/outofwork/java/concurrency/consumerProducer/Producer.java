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
public class Producer implements Runnable {

    private final Lock lock;
    private final Condition addData;
    private final Condition removeData;
    private final CountDownLatch countDownLatch;
    private final BlockingQueue<Integer> blockingQueue;

    public Producer(Lock lock, Condition addData, Condition removeData, CountDownLatch countDownLatch, BlockingQueue<Integer> blockingQueue) {
        this.lock = lock;
        this.addData = addData;
        this.removeData = removeData;
        this.countDownLatch = countDownLatch;
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        producer();
        countDownLatch.countDown();
    }

    public void producer() {

        System.out.println("Starting Producer");
        try {
            for (int i = 0; i < 100; i++) {
                blockingQueue.offer(i, 1, TimeUnit.MINUTES);
                ConsumerProducer.count.incrementAndGet();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
