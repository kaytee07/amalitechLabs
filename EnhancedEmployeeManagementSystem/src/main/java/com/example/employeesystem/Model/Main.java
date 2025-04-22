package com.example.employeesystem.Model;

import com.example.employeesystem.Exception.EmployeeNotFoundException;

import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        EmployeeDatabase<UUID> db = new EmployeeDatabase<>();
        try {
            UUID employeeID1 = db.addEmployee(new Employee<>("Alice", "Engineering", 60000, 4, 101));
            UUID employeeID2 = db.addEmployee(new Employee<>("Bob", "Human Resource", 75000, 5, 102));
            UUID employeeID3 = db.addEmployee(new Employee<>("Charlie", "Quality Control", 50000, 3, 103));
            UUID employeeID4 = db.addEmployee(new Employee<>("Diana", "Engineering", 80000, 5, 104));
            UUID employeeID5 = db.addEmployee(new Employee<>("Eve", "Human Resource", 70000, 4, 105));

            System.out.println("🔸 All Employees:");
            db.getAllEmployees().forEach(emp -> {
                System.out.println("Name: " + emp.getName() +
                        ", Department: " + emp.getDepartment() +
                        ", Salary: " + emp.getSalary() +
                        ", Experience: " + emp.getYearsOfExperience() +
                        ", Staff ID: " + emp.getEmployeeID());
            });

            // Raise Charlie's salary by 10%
            db.salaryRaise(0.1); // You might want to implement logic that only applies to Charlie
            System.out.println("\n💰 After Raising Charlie's Salary:");
            db.getAllEmployees().forEach(emp -> {
                System.out.println("Name: " + emp.getName() +
                        ", Department: " + emp.getDepartment() +
                        ", Salary: " + emp.getSalary() +
                        ", Experience: " + emp.getYearsOfExperience() +
                        ", Staff ID: " + emp.getEmployeeID());
            });

            // Remove Bob
            db.removeEmployee(employeeID2);
            System.out.println("\n❌ After Removing Bob:");
            db.getAllEmployees().forEach(emp -> {
                System.out.println("Name: " + emp.getName() +
                        ", Department: " + emp.getDepartment() +
                        ", Salary: " + emp.getSalary() +
                        ", Experience: " + emp.getYearsOfExperience() +
                        ", Staff ID: " + emp.getEmployeeID());
            });

            db.updateEmployeeDetail(employeeID5, "department", "Finance");

// Update Diana's salary
            db.updateEmployeeDetail(employeeID4, "salary", 90000.0);

            System.out.println("\n🛠️ After Updating Employee Details:");
            db.getAllEmployees().forEach(emp -> {
                System.out.println("Name: " + emp.getName() +
                        ", Department: " + emp.getDepartment() +
                        ", Salary: " + emp.getSalary() +
                        ", Experience: " + emp.getYearsOfExperience() +
                        ", Staff ID: " + emp.getEmployeeID());
            });


            // Top 3 highest paid
            System.out.println("\n🏆 Top 3 Highest Paid Employees:");
            db.getTopNHighestPaid(3).forEach(emp -> {
                System.out.println("Name: " + emp.getName() +
                        ", Salary: " + emp.getSalary());
            });

            db.displayEmployeesWithForLoop();
            db.printDepartmentReport();
            
            

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

