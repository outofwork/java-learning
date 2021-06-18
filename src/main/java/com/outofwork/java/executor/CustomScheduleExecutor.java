package com.outofwork.java.executor;

import java.util.concurrent.*;

/**
 * @author outofwork
 * created on 14/02/21
 */
public class CustomScheduleExecutor {

    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);

        executorService.scheduleAtFixedRate(new TaskRunnable(1), 2, 2, TimeUnit.SECONDS);
        Future<Integer> future = executorService.submit(new TaskCallable());

        try {
            Thread.sleep(1000);
            if (future.isDone()) {
                Integer value = future.get(5, TimeUnit.SECONDS);
                System.out.println("Value from Callable Method: " + value);
            }

        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }


        try {

            executorService.shutdown();
            executorService.awaitTermination(20, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
