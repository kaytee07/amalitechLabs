package com.example.employeesystem.Model;

import com.example.employeesystem.Exception.UserNotFoundException;

import java.util.*;
import java.util.stream.Collectors;

public class EmployeeDatabase <T> {
    
    private HashMap<UUID, Employee<T>> employees = new HashMap<>();

    public UUID addEmployee(Employee<T> employee){
        UUID id = UUID.randomUUID();
        employees.put(id, employee);
        return id;
    }

    public void removeEmployee(T employeeId) throws UserNotFoundException {
        if(!employees.containsKey(employeeId)) throw new  UserNotFoundException("eRROR REMOVING EMPLOYEE");
        employees.remove(employeeId);
    }

    public void updateEmployeeDetail(T employeeId, String field, Object newValue) throws Exception {
        Employee<T> employee = employees.get(employeeId);
        if (employee == null){
            throw new UserNotFoundException("User not found");
        }
        switch (field){
            case "name" -> {
                if (newValue instanceof  String){
                    employee.setName((String) newValue);
                }
            }
            case "department" -> {
                if (newValue instanceof String){
                    employee.setDepartment((String) newValue);
                }
            }
            case "salary" -> {
                if (newValue instanceof Double){
                    employee.setSalary((Double) newValue);
                }
            }

            case "performanceRating" -> {
                if (newValue instanceof Double){
                    employee.setPerformanceRating((Double) newValue);
                }
            }

            case "yearsOfExperience" -> {
                if (newValue instanceof Integer){
                    employee.setYearsOfExperience((Integer) newValue);
                }
            }

            case "isActive" -> {
                if (newValue instanceof  Boolean){
                    employee.setIsActive();
                }
            }

            default -> throw new Exception("Invalid field");
        }

    }


    public ArrayList<Employee<T>> getAllEmployees(){
        return new ArrayList<Employee<T>>(employees.values());
    }


    public ArrayList<Employee<T>> getFilteredList(String filter, String query) throws Exception{
        ArrayList<Employee<T>> allEmployees = getAllEmployees();
        ArrayList<Employee<T>> filteredEmployees;
        switch(filter){
            case "department" -> {
                filteredEmployees = allEmployees.stream().filter(employee -> Objects.equals(employee.getDepartment(), filter)).collect(Collectors.toCollection(ArrayList::new));
                return filteredEmployees;
            }
            case "names" -> {
                filteredEmployees = allEmployees.stream().filter(employee -> employee.getName().contains(query)).collect(Collectors.toCollection(ArrayList::new));
                return filteredEmployees;
            }
            default -> throw new Exception("Invalid filter");
        }
    }

    public ArrayList<Employee<T>> getFilteredList(String filter, Double query) throws Exception{
        ArrayList<Employee<T>> allEmployees = getAllEmployees();
        ArrayList<Employee<T>> filteredEmployees;
        switch(filter){
            case "performancerating" -> {
                filteredEmployees = allEmployees.stream().filter(employee -> employee.getPerformanceRating() >= 4.0).collect(Collectors.toCollection(ArrayList::new));
                return filteredEmployees;
            }
            case "salary" -> {
                filteredEmployees = allEmployees.stream().filter(employee -> employee.getSalary() >= 50000 && employee.getSalary() <= 100000).collect(Collectors.toCollection(ArrayList::new));
                return filteredEmployees;
            }
            default -> throw new Exception("Invalid filter");
        }
    }

    public void salaryRaise(Double percentageInDecimal){
        ArrayList<Employee<T>> allEmployees = getAllEmployees();
        allEmployees.forEach(employee -> {
            if (employee.getPerformanceRating() >= 4.5) {
                double newSalary = employee.getSalary() + employee.getSalary() * percentageInDecimal;
                employee.setSalary(newSalary);
            }
        } );
    }

    public ArrayList<Employee<T>> getTopNHighestPaid(int N){
        ArrayList<Employee<T>> allEmployees = getAllEmployees();
        ArrayList<Employee<T>> topNEmployees = allEmployees.stream().sorted(bySalary).limit(N).collect(Collectors.toCollection(ArrayList::new));
        return topNEmployees;
    }

    Comparator<Employee<T>> bySalary = new Comparator<Employee<T>>() {
        @Override
        public int compare(Employee firstEmployee, Employee secondEmployee) {
            return secondEmployee.getSalary().compareTo(firstEmployee.getSalary());
        }
    };

    Comparator<Employee<T>> byPerformance = new Comparator<Employee<T>>() {
        @Override
        public int compare(Employee firstEmployee, Employee secondEmployee){
            return  secondEmployee.getPerformanceRating().compareTo(firstEmployee.getPerformanceRating());
        }
    };



}
