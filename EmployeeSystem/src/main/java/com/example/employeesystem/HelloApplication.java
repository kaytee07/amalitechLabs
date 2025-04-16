package com.example.employeesystem;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Simple Employee Management");

        // 1. Create the TableView to display employees
        TableView<Employee> table = createEmployeeTable();

        // 2. Create form for adding new employees
        GridPane addForm = createAddForm(table);

        // 3. Create action buttons
        HBox buttonBox = createActionButtons(table);

        // 4. Combine everything in a layout
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        root.getChildren().addAll(table, addForm, buttonBox);

        // 5. Set up the scene and show the stage
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private TableView<Employee> createEmployeeTable() {
        TableView<Employee> table = new TableView<>();

        // Create columns
        TableColumn<Employee, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Employee, String> deptCol = new TableColumn<>("Department");
        deptCol.setCellValueFactory(new PropertyValueFactory<>("department"));

        TableColumn<Employee, Double> salaryCol = new TableColumn<>("Salary");
        salaryCol.setCellValueFactory(new PropertyValueFactory<>("salary"));

        // Add columns to table
        table.getColumns().addAll(nameCol, deptCol, salaryCol);

        // Add sample data
        ObservableList<Employee> employees = FXCollections.observableArrayList(
                new Employee("John Doe", "IT", 75000),
                new Employee("Jane Smith", "HR", 65000),
                new Employee("Bob Johnson", "Finance", 85000)
        );
        table.setItems(employees);

        return table;
    }

    private GridPane createAddForm(TableView<Employee> table) {
        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);
        form.setPadding(new Insets(10));

        // Create form fields
        TextField nameField = new TextField();
        TextField deptField = new TextField();
        TextField salaryField = new TextField();

        // Add labels and fields to form
        form.add(new Label("Name:"), 0, 0);
        form.add(nameField, 1, 0);
        form.add(new Label("Department:"), 0, 1);
        form.add(deptField, 1, 1);
        form.add(new Label("Salary:"), 0, 2);
        form.add(salaryField, 1, 2);

        // Add button
        Button addButton = new Button("Add Employee");
        addButton.setOnAction(e -> {
            try {
                String name = nameField.getText();
                String department = deptField.getText();
                double salary = Double.parseDouble(salaryField.getText());

                table.getItems().add(new Employee(name, department, salary));

                // Clear fields after adding
                nameField.clear();
                deptField.clear();
                salaryField.clear();
            } catch (NumberFormatException ex) {
                showAlert("Error", "Please enter a valid salary");
            }
        });

        form.add(addButton, 1, 3);

        return form;
    }

    private HBox createActionButtons(TableView<Employee> table) {
        HBox box = new HBox(10);
        box.setPadding(new Insets(10));

        // Remove button
        Button removeButton = new Button("Remove Selected");
        removeButton.setOnAction(e -> {
            Employee selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                table.getItems().remove(selected);
            } else {
                showAlert("Error", "No employee selected");
            }
        });

        // Sort by name button
        Button sortNameButton = new Button("Sort by Name");
        sortNameButton.setOnAction(e -> {
            table.getItems().sort((e1, e2) -> e1.getName().compareTo(e2.getName()));
        });

        // Sort by salary button
        Button sortSalaryButton = new Button("Sort by Salary");
        sortSalaryButton.setOnAction(e -> {
            table.getItems().sort((e1, e2) -> Double.compare(e2.getSalary(), e1.getSalary()));
        });

        box.getChildren().addAll(removeButton, sortNameButton, sortSalaryButton);
        return box;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Simplified Employee class as inner class
    public static class Employee {
        private String name;
        private String department;
        private double salary;

        public Employee(String name, String department, double salary) {
            this.name = name;
            this.department = department;
            this.salary = salary;
        }

        public String getName() {
            return name;
        }

        public String getDepartment() {
            return department;
        }

        public double getSalary() {
            return salary;
        }
    }
}