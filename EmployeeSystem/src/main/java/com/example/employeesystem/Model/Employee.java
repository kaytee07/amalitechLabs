package com.example.employeesystem.Model;

public class Employee <T> implements Comparable<Employee<T>> {
    private T employeeId;
    String name;
    String department;
    double salary;
    double performanceRating;
    int yearsOfExperience;
    boolean isActive;

    Employee(){

    }

    @Override
    public int compareTo(Employee<T> otherEmployee) {
        return otherEmployee.yearsOfExperience - this.yearsOfExperience;
    }
}
