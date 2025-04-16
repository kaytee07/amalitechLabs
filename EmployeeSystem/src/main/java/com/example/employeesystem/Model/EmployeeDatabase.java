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


    public ArrayList<Employee<T>> searchByName(String filter, String query) throws Exception{
        ArrayList<Employee<T>> allEmployees = getAllEmployees();
        ArrayList<Employee<T>> filteredEmployees;
        filteredEmployees = allEmployees.stream().filter(employee -> employee.getName().contains(query)).collect(Collectors.toCollection(ArrayList::new));
        if(filteredEmployees.isEmpty()) throw new Exception("User not found");
        return filteredEmployees;

    }

    public ArrayList<Employee<T>> filterByDepartment(String department) throws Exception{
        ArrayList<Employee<T>> allEmployees = getAllEmployees();
        ArrayList<Employee<T>> filteredEmployees;
        filteredEmployees = allEmployees.stream()
                .filter(employee -> Objects.equals(employee.getDepartment(), department))
                .collect(Collectors.toCollection(ArrayList::new));
        if(filteredEmployees.isEmpty()) throw new Exception("No employee exist in this department");
        return filteredEmployees;
    }

    public ArrayList<Employee<T>> filterBySalaryRange(double minRange, double maxRange) throws Exception{
        ArrayList<Employee<T>> allEmployees = getAllEmployees();
        ArrayList<Employee<T>> filteredEmployees;
        filteredEmployees = allEmployees.stream()
                .filter(employee -> employee.getSalary() >= minRange && employee.getSalary() <= maxRange)
                .collect(Collectors.toCollection(ArrayList::new));
        if(filteredEmployees.isEmpty()) throw new Exception("No employee falls within this range");
        return filteredEmployees;
    }

    public ArrayList<Employee<T>> filterByPerformance(boolean greaterThan, double performance) throws Exception{
        ArrayList<Employee<T>> allEmployees = getAllEmployees();
        ArrayList<Employee<T>> filteredEmployees;
        if(greaterThan){
            filteredEmployees = getAllEmployees().stream()
                    .filter(employee -> employee.getPerformanceRating() > performance)
                    .collect(Collectors.toCollection(ArrayList::new));
        } else {
            filteredEmployees = getAllEmployees().stream()
                    .filter(employee -> employee.getPerformanceRating() < performance)
                    .collect(Collectors.toCollection(ArrayList::new));
        }
        if(filteredEmployees.isEmpty()){
            throw new Exception("no user falls within the range specified");
        }
        return filteredEmployees;
    }

    public double AverageSalaryByDepartment(String department){
        return employees.values().stream()
                .filter(emp -> Objects.equals(emp.getDepartment(), department))
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0.0);
    }

    public void salaryRaise(Double percentageInDecimal){
        employees.values().forEach(employee -> {
            if (employee.getPerformanceRating() >= 4.5) {
                double newSalary = employee.getSalary() + employee.getSalary() * percentageInDecimal;
                employee.setSalary(newSalary);
            }
        } );
    }

    public ArrayList<Employee<T>> getTopNHighestPaid(int N){
        ArrayList<Employee<T>> allEmployees = getAllEmployees();
        ArrayList<Employee<T>> topNEmployees = allEmployees.stream()
                .sorted(bySalary).limit(N)
                .collect(Collectors.toCollection(ArrayList::new));
        return topNEmployees;
    }

    public  void printHeader() {
        System.out.printf("%-15s %-20s %-10s %-10s %-10s %-20s%n",
                "Name", "Department", "Salary", "Performance", "Years Of Experience", "Employee ID");
        System.out.println("---------------------------------------------------------------------");
    }

    public void displayEmployeesWithForLoop() {
        ArrayList<Employee<T>> allEmployees = getAllEmployees();
        printHeader();
        for (Employee<T> employee: allEmployees){
            System.out.printf("%-15s %-20s %-10s %-10s %-10s %-20s%n",
                    employee.getName(),
                    employee.getDepartment(),
                    employee.getSalary(),
                    employee.getPerformanceRating(),
                    employee.getYearsOfExperience(),
                    employee.getEmployeeId().toString());
        }
    }

    public void displayEmployeesWithStream() {
        ArrayList<Employee<T>> allEmployees = getAllEmployees();
        printHeader();
        allEmployees.stream()
                .map(employee -> String.format("%-15s %-20s %-10s %-10s %-10s %-20s%n",
                        employee.getName(),
                        employee.getDepartment(),
                        employee.getSalary(),
                        employee.getPerformanceRating(),
                        employee.getYearsOfExperience(),
                        employee.getEmployeeId().toString()))
                .forEach(System.out::println);
    }

    public static final Comparator<Employee<?>> bySalary = new Comparator<Employee<?>>() {
        @Override
        public int compare(Employee firstEmployee, Employee secondEmployee) {
            return secondEmployee.getSalary().compareTo(firstEmployee.getSalary());
        }
    };

    public static final Comparator<Employee<?>> byPerformance = new Comparator<Employee<?>>() {
        @Override
        public int compare(Employee firstEmployee, Employee secondEmployee){
            return  secondEmployee.getPerformanceRating().compareTo(firstEmployee.getPerformanceRating());
        }
    };



}
