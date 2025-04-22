package com.example.employeesystem.Model;

import com.example.employeesystem.Exception.*;

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
                    double performanceRating, int yearsOfExperience, T employeeID) throws Exception {
        validateName(name);
        validateDepartment(department);
        validateSalary(salary);
        validatePerformanceRating(performanceRating);
        validateYearsOfExperience(yearsOfExperience);
        validateEmployeeId(employeeID.toString());

        this.employeeId = employeeID;
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.performanceRating = performanceRating;
        this.yearsOfExperience = yearsOfExperience;
        this.isActive = true;
    }


    public Employee(String name, String department, double salary,
                    double performanceRating, int yearsOfExperience) throws Exception {

        validateName(name);
        validateDepartment(department);
        validateSalary(salary);
        validatePerformanceRating(performanceRating);
        validateYearsOfExperience(yearsOfExperience);

        this.employeeId = (T) UUID.randomUUID();
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.performanceRating = performanceRating;
        this.yearsOfExperience = yearsOfExperience;
        this.isActive = true;
    }

    private void validateName(String name) throws InvalidNameException {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidNameException("Name cannot be empty or null.");
        }
    }

    private void validateDepartment(String department) throws InvalidDepartmentException {
        if (department == null || department.trim().isEmpty()) {
            throw new InvalidDepartmentException("Department cannot be empty or null.");
        }
    }

    private void validateSalary(double salary) throws InvalidSalaryException {
        if (salary < 0) {
            throw new InvalidSalaryException("Salary cannot be negative.");
        }
    }

    private void validatePerformanceRating(double rating) throws InvalidPerformanceRatingException {
        if (rating < 0 || rating > 5) {
            throw new InvalidPerformanceRatingException("Performance rating must be between 0 and 5.");
        }
    }

    private void validateYearsOfExperience(int years) throws InvalidExperienceException {
        if (years < 0) {
            throw new InvalidExperienceException("Years of experience cannot be negative.");
        }
    }

    private void validateEmployeeId(String id) throws InvalidEmployeeIdException {
        if (id == null || id.trim().isEmpty()) {
            throw new InvalidEmployeeIdException("Employee ID cannot be null or empty.");
        }
    }

    public void setName(String name) throws Exception {
        validateName(name);
        this.name = name;
    }

    public String getName () { return name; }

    public void setDepartment (String department) throws Exception {
        validateDepartment(department);
        this.department = department;
    }

    public String getDepartment(){ return department;}

    public void setSalary(double salary) throws Exception {
        validateSalary(salary);
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
