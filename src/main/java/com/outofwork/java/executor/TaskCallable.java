package com.outofwork.java.executor;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 * @author outofwork
 * created on 14/02/21
 */
public class TaskCallable implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        return new Random().nextInt();
    }
}
