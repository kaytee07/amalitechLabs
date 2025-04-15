package com.example.employeesystem.Model;

import com.example.employeesystem.Exception.UserNotFoundException;

import java.util.HashMap;

public class EmployeeDatabase <T>{
    
    private HashMap<T, Employee<T>> employees = new HashMap<>();

    public void addEmployee(T employeeId,  Employee<T> employee){
        employees.put(employeeId, employee);
        return;
    }

    public void removeEmployee(T employeeId) throws UserNotFoundException {
        employees.remove(employeeId);
    }

    public void updateEmployeeDetail(T employeeId, String field, Object newValue) throws UserNotFoundException{
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
                if (newValue instanceof  )
            }
        }

    }

    public void getAllEmployees(){

    }
}
