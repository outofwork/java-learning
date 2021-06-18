package com.outofwork.java.streams;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author outofwork
 * created on 14/02/21
 */
public class JavaStream {

    public static void main(String[] args) {
        int[] number = new int[]{1, 2, 3, 4, 5, 6, 7};

        IntStream.of(number)
                .min()  //max average count sum of all the numbers
                .ifPresent(System.out::println);

        IntSummaryStatistics statistics = IntStream.of(number).summaryStatistics();
        System.out.println(statistics.getMax());

        IntStream.of(number)
                .distinct()
                .sorted()
                .limit(4)
                .forEach(System.out::println);
        /*
         * Stream --> Distinct --> Sort --> Limit --> Sum(or any Statistical operation)
         */
        Integer evenSum = IntStream.of(number)
                .distinct()
                .filter(n -> n % 2 == 0)
                .sum();

        objectStream();
    }


    public static void objectStream() {

        List<Employees> employeesList = new ArrayList<>();
        employeesList.add(new Employees.EmployeesBuilder().setName("Hello").setId(123).setSalary(123123).build());
        employeesList.add(new Employees.EmployeesBuilder().setName("Hello World").setId(1234).setSalary(123145423).build());
        employeesList.add(new Employees.EmployeesBuilder().setName("Hello Atlassian").setId(1235).setSalary(1445454123).build());

        List<String> employeeNames = employeesList.stream()
                .sorted(Comparator.comparingInt(Employees::getSalary))
                .filter(employees -> employees.getId() > 100)
                .limit(5)
                .map(Employees::getName)
                .collect(Collectors.toList());

        employeeNames.forEach(System.out::println);
    }
}
