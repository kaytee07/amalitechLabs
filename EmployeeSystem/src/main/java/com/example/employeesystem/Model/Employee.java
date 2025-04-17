package com.example.employeesystem.Model;

import java.util.Comparator;
import java.util.UUID;

public class Employee <T> implements Comparable<Employee<T>>{
    private final T employeeId;
    private String name;
    private String department;
    private Double salary;
    private Double performanceRating;
    private Integer yearsOfExperience;
    private boolean isActive;


    public Employee(String name, String department, double salary,
                    double performanceRating, int yearsOfExperience){
        this.employeeId = (T) UUID.randomUUID();
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.performanceRating = performanceRating;
        this.yearsOfExperience = yearsOfExperience;
        this.isActive = true;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName () { return name; }

    public void setDepartment(String department){
        this.department = department;
    }

    public String getDepartment(){ return department;}

    public void setSalary(double salary){
        this.salary = salary;
    }

    public Double getSalary(){ return salary; }

    public void setPerformanceRating(double performanceRating){
        this.performanceRating = performanceRating;
    }

    public Double getPerformanceRating(){
        return performanceRating;
    }

    public void setYearsOfExperience(int yearsOfExperience){
        this.yearsOfExperience = yearsOfExperience;
    }

    public int getYearsOfExperience(){
        return  yearsOfExperience;
    }

    public void setIsActive() {
        isActive = !isActive;
    }

    public boolean getIsActive(){ return isActive; }

    public T getEmployeeID(){ return employeeId; }


    @Override
    public int compareTo(Employee<T> otherEmployee) {
        return otherEmployee.yearsOfExperience - this.yearsOfExperience;
    }
}
