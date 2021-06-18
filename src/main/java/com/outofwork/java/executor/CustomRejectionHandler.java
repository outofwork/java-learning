package com.outofwork.java.executor;

import java.util.concurrent.*;

/**
 * @author outofwork
 * created on 14/02/21
 */
public class CustomRejectionHandler {

    public static void main(String[] args) {

        ExecutorService service = new ThreadPoolExecutor(
                10,
                10,
                120, TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(120),
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        System.out.println("Custom Rejection Handler");
                    }
                }
        );

        try {
            service.submit(new TaskRunnable(1));
        } catch (RejectedExecutionException e) {
            e.printStackTrace();
        }

        if (service.isTerminated()) {
            System.out.println("Shutdown Completed.");
        }
    }
}
