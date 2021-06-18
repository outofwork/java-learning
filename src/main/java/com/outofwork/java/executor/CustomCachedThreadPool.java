package com.outofwork.java.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author outofwork
 * created on 14/02/21
 */
public class CustomCachedThreadPool {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        /*
         * Kills an idle thread.
         * Theoretically can create as much task.
         */
        for (int i = 0; i < 10; i++) {
            executorService.submit(new TaskRunnable(i));
        }
        try {
            executorService.shutdownNow();
            executorService.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
