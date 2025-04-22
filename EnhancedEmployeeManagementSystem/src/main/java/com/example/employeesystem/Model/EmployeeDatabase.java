package com.example.employeesystem.Model;

import com.example.employeesystem.Exception.EmployeeNotFoundException;
import com.example.employeesystem.Exception.InvalidEmployeeIdException;
import com.example.employeesystem.Exception.InvalidSalaryException;

import java.util.*;
import java.util.stream.Collectors;

public class EmployeeDatabase <T> {
    
    private final HashMap<T, Employee<T>> employees = new HashMap<>();

    public T addEmployee(Employee<T> employee){
        employees.put(employee.getEmployeeID(), employee);
        return employee.getEmployeeID();
    }

    public Employee<T> getEmployee(T employeeID) throws Exception {
        Employee<T> employee = employees.get(employeeID);
        if (employee == null) throw new EmployeeNotFoundException("User not found");

        return employee;
    }

    public void removeEmployee(T employeeID) throws Exception {
        Employee<T> employee = employees.get(employeeID);
        if (employee == null) throw new EmployeeNotFoundException("employee ID doesn't match anyone in the system");
        employees.remove(employeeID);
    }

    public void updateEmployeeDetail(T employeeId, String field, Object newValue) throws Exception {
        Employee<T> employee = employees.get(employeeId);
        if (employee == null){
            throw new EmployeeNotFoundException("User not found");
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
        Iterator<Employee<T>> iterator = new ArrayList<>(employees.values()).iterator();
        while (iterator.hasNext()) {
            Employee<T> employee = iterator.next();
            System.out.println(employee.getName());
        }

        return new ArrayList<Employee<T>>(employees.values());
    }


    public ArrayList<Employee<T>> search(String query) throws Exception {
        String lowerQuery = query.toLowerCase();
        ArrayList<Employee<T>> allEmployees = getAllEmployees();

        ArrayList<Employee<T>> filteredEmployees = allEmployees.stream()
                .filter(employee ->
                        employee.getName().toLowerCase().contains(lowerQuery) ||
                                employee.getDepartment().toLowerCase().contains(lowerQuery)
                )
                .collect(Collectors.toCollection(ArrayList::new));

        if (filteredEmployees.isEmpty()) {
            throw new EmployeeNotFoundException("No employee matched the search query.");
        }

        return filteredEmployees;
    }


    public ArrayList<Employee<T>> filterByDepartment(String department) throws Exception{
        if (department != "HR"
                || department != "Marketting"
                || department != "Engineering"
                || department != "Sales"){
            throw new InvalidEmployeeIdException("Invalid Input as Department");
        }
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
                try {
                    double newSalary = employee.getSalary() + employee.getSalary() * percentageInDecimal;
                    employee.setSalary(newSalary);
                } catch(InvalidSalaryException e){
                    System.out.println(e.getMessage());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

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
        System.out.printf("%-15s %-20s %-10s %-10s %-20s %-20s%n",
                "Name", "Department", "Salary", "Performance", "Experience", "EmployeeID");
        System.out.println("---------------------------------------------------------------------");
    }

    public void displayEmployeesWithForLoop() {
        ArrayList<Employee<T>> allEmployees = getAllEmployees();
        printHeader();
        for (Employee<T> employee: allEmployees){
            System.out.printf("%-15s %-20s %-10s %-10s %-20s %-20s%n",
                    employee.getName(),
                    employee.getDepartment(),
                    employee.getSalary(),
                    employee.getPerformanceRating(),
                    employee.getYearsOfExperience(),
                    employee.getEmployeeID().toString());
        }
    }

    public void printDepartmentReport() {
        Map<String, List<Employee<?>>> groupedByDept = getAllEmployees().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
        System.out.println(String.format("%-15s %-15s %-25s %-20s", "Department", "Avg Salary", "Avg Performance Rating", "Avg Experience"));
        System.out.println("---------------------------------------------------------------");
        groupedByDept.forEach((dept, empList) -> {
            double avgSalary = empList.stream()
                    .collect(Collectors.averagingDouble(Employee::getSalary));
            double avgPerformance = empList.stream()
                    .collect(Collectors.averagingDouble(Employee::getPerformanceRating));
            double avgExperience = empList.stream()
                    .collect(Collectors.averagingInt(Employee::getYearsOfExperience));
            System.out.printf("%-15s $%-14.2f %-25.2f %-20.2f%n", dept, avgSalary, avgPerformance, avgExperience);
        });
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
