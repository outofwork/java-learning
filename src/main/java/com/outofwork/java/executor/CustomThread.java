package com.outofwork.java.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author outofwork
 * created on 14/02/21
 */
public class CustomThread {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            executorService.submit(new TaskRunnable(i));
        }

        try {
            executorService.shutdownNow();
            executorService.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread Started.");
    }
}