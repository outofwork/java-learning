package com.outofwork.java.streams;

/**
 * @author outofwork
 * created on 14/02/21
 */
public class Employees {

    private String name;
    private int salary;
    private int Id;

    public String getName() {
        return name;
    }

    public int getSalary() {
        return salary;
    }

    public int getId() {
        return Id;
    }

    public static final class EmployeesBuilder {
        private String name;
        private int salary;
        private int Id;

        public EmployeesBuilder() {
        }

        public static EmployeesBuilder anEmployees() {
            return new EmployeesBuilder();
        }

        public EmployeesBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public EmployeesBuilder setSalary(int salary) {
            this.salary = salary;
            return this;
        }

        public EmployeesBuilder setId(int Id) {
            this.Id = Id;
            return this;
        }

        public Employees build() {
            Employees employees = new Employees();
            employees.salary = this.salary;
            employees.Id = this.Id;
            employees.name = this.name;
            return employees;
        }
    }
}
