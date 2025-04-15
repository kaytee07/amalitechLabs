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


    }

    public void getAllEmployees(){

    }
}
