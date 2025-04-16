package com.example.employeesystem;

import com.example.employeesystem.Model.Employee;
import com.example.employeesystem.Model.EmployeeDatabase;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.UUID;

public class HelloApplication extends Application {

    // Our database instance
    private EmployeeDatabase<UUID> employeeDatabase = new EmployeeDatabase<>();
    private ObservableList<Employee<UUID>> employeeList = FXCollections.observableArrayList();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Employee Management System");

        initializeSampleData();
        refreshEmployeeList();

        TableView<Employee<UUID>> table = createEmployeeTable();

        GridPane addForm = createAddForm();

        HBox buttonBox = createActionButtons(table);

        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        root.getChildren().addAll(table, addForm, buttonBox);

        Scene scene = new Scene(root, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

//    private void initializeSampleData() {
//        employeeDatabase.addEmployee(new Employee<>("John Doe", "IT", 75000.0, 4.5, 5));
//        employeeDatabase.addEmployee(new Employee<>("Jane Smith", "HR", 65000.0, 4.2, 3));
//    }

    private void refreshEmployeeList() {
        employeeList.setAll(employeeDatabase.getAllEmployees());
    }

    private TableView<Employee<UUID>> createEmployeeTable() {
        TableView<Employee<UUID>> table = new TableView<>();

        TableColumn<Employee<UUID>, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Employee<UUID>, String> deptCol = new TableColumn<>("Department");
        deptCol.setCellValueFactory(new PropertyValueFactory<>("department"));

        TableColumn<Employee<UUID>, Double> salaryCol = new TableColumn<>("Salary");
        salaryCol.setCellValueFactory(new PropertyValueFactory<>("salary"));

        TableColumn<Employee<UUID>, Double> performanceCol = new TableColumn<>("Performance");
        performanceCol.setCellValueFactory(new PropertyValueFactory<>("PerformanceRating"));

        TableColumn<Employee<UUID>, Double> yearsOfExperienceCol = new TableColumn<>("Experience");
        yearsOfExperienceCol.setCellValueFactory(new PropertyValueFactory<>("yearsOfExperience"));


        table.getColumns().addAll(nameCol, deptCol, salaryCol, performanceCol, yearsOfExperienceCol);
        table.setItems(employeeList);

        return table;
    }

    private GridPane createAddForm() {
        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);
        form.setPadding(new Insets(10));

        TextField nameField = new TextField();
        TextField deptField = new TextField();
        TextField salaryField = new TextField();
        TextField performanceField = new TextField();
        TextField yearsOfExperienceField = new TextField();

        form.add(new Label("Name:"), 0, 0);
        form.add(nameField, 1, 0);
        form.add(new Label("Department:"), 0, 1);
        form.add(deptField, 1, 1);
        form.add(new Label("Salary:"), 0, 2);
        form.add(salaryField, 1, 2);
        form.add(new Label("Performance rating:"), 0, 3);
        form.add(performanceField,1 ,3);
        form.add(new Label("years of experience:"), 0, 4);
        form.add(yearsOfExperienceField,1 ,4);

        Button addButton = new Button("Add Employee");
        addButton.setOnAction(e -> {
            try {
                String name = nameField.getText();
                String department = deptField.getText();
                double salary = Double.parseDouble(salaryField.getText());
                double performance = Double.parseDouble(performanceField.getText());
                int experience = Integer.parseInt(yearsOfExperienceField.getText());

                employeeDatabase.addEmployee(new Employee<>(name, department, salary, performance, experience));
                refreshEmployeeList();


                nameField.clear();
                deptField.clear();
                salaryField.clear();
                performanceField.clear();
                yearsOfExperienceField.clear();
            } catch (NumberFormatException ex) {
                showAlert("Error", "Please enter a valid value");
            }
        });

        form.add(addButton, 1, 5);
        return form;
    }


    private HBox createActionButtons(TableView<Employee<UUID>> table) {
        HBox box = new HBox(10);
        box.setPadding(new Insets(10));

        Button removeButton = new Button("Remove Selected");
        removeButton.setOnAction(e -> {
            Employee<UUID> selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                try {
                    employeeDatabase.removeEmployee(selected.getEmployeeId());

                    refreshEmployeeList();
                } catch (Exception ex) {
                    showAlert("Error", ex.getMessage());
                }
            } else {
                showAlert("Error", "No employee selected");
            }
        });


        Button sortNameButton = new Button("Sort by Name");
        sortNameButton.setOnAction(e -> {
            employeeList.sort((e1, e2) -> e1.getName().compareTo(e2.getName()));
        });

        box.getChildren().addAll(removeButton, sortNameButton);
        return box;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}