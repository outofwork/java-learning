package com.outofwork.java.concurrency.consumerProducer;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author outofwork
 * created on 14/02/21
 */
public class ConsumerProducer {

    public static final AtomicInteger count = new AtomicInteger(0);
    public static final int MAX_COUNT = 100;
    private static final Lock lock = new ReentrantLock();
    private static final Condition addData = lock.newCondition();
    private static final Condition removeData = lock.newCondition();
    private static final BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(MAX_COUNT);

    private static final CountDownLatch countDownLatch = new CountDownLatch(2);


    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(new Producer(lock, addData, removeData, countDownLatch, queue));
        executorService.submit(new Consumer(lock, addData, removeData, countDownLatch, queue));

        try {
            countDownLatch.await();
            System.out.println("Producer and Consumer Completed Successfully.");
            executorService.shutdown();
            executorService.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
