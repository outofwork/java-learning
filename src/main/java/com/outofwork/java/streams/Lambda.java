package com.outofwork.java.streams;

import java.util.Comparator;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author outofwork
 * created on 14/02/21
 */
public class Lambda {

    public static void main(String[] args) {

        AtomicReference<Integer> atomicReference = new AtomicReference<>();

        Comparator<Employees> comparator = new Comparator<Employees>() {
            @Override
            public int compare(Employees o1, Employees o2) {
                return 0;
            }
        };

        Comparator<Employees> comparatorLambdaString = Comparator.comparing(Employees::getName);
        Comparator<Employees> comparatorLambdaInt = Comparator.comparingInt(Employees::getId);
    }
}
