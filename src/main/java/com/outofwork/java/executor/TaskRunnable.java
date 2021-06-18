package com.outofwork.java.executor;

/**
 * @author outofwork
 * created on 14/02/21
 */
class TaskRunnable implements Runnable {

    private final int thread;

    public TaskRunnable(int threadID) {
        this.thread = threadID;
    }

    @Override
    public void run() {
        System.out.println("Hello: " + thread);
    }
}
