package com.example.employeesystem;

import com.example.employeesystem.Exception.EmployeeNotFoundException;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class HelloApplication extends Application {

    // Our database instance
    private EmployeeDatabase<UUID> employeeDatabase = new EmployeeDatabase<>();
    private ObservableList<Employee<UUID>> employeeList = FXCollections.observableArrayList();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Employee Management System");

        TextField searchField = createSearchField(employeeList);
        HBox performanceFilter = createPerformanceFilterPane(employeeList);
        VBox searchAndFilter = new VBox(5, searchField, performanceFilter);

        initializeDummyData();
        refreshEmployeeList();


        TableView<Employee<UUID>> table = createEmployeeTable();


        GridPane addForm1 = createAddForm();
        GridPane addForm2 = createUpdateForm();

        HBox buttonBox = createActionButtons(table);

        VBox root = new VBox(10);
        HBox forms = new HBox(10);
        forms.getChildren().addAll(addForm1, addForm2);

        root.setPadding(new Insets(20));

        root.getChildren().addAll(searchAndFilter, table, forms, buttonBox);

        Scene scene = new Scene(root, 700, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


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

        TableColumn<Employee<UUID>, UUID> employeeIDCol = new TableColumn<>("employeeID");
        employeeIDCol.setCellValueFactory(new PropertyValueFactory<>("employeeID"));


        table.getColumns().addAll(nameCol, deptCol, salaryCol, performanceCol, yearsOfExperienceCol, employeeIDCol);
        table.setItems(employeeList);

        return table;
    }

    private void initializeDummyData() throws Exception {
        Employee<UUID> emp1 = new Employee<>("Alice", "HR", 3500.0, 4.2, 5);
        Employee<UUID> emp2 = new Employee<>( "Bob", "Engineering", 5000.0, 3.8, 7);
        Employee<UUID> emp3 = new Employee<>( "Charlie", "Sales", 4200.0, 4.5, 3);
        Employee<UUID> emp4 = new Employee<>( "Diana", "Marketing", 3900.0, 4.1, 4);
        Employee<UUID> emp5 = new Employee<>( "Ethan", "Engineering", 6000.0, 4.8, 10);

        employeeDatabase.addEmployee(emp1);
        employeeDatabase.addEmployee(emp2);
        employeeDatabase.addEmployee(emp3);
        employeeDatabase.addEmployee(emp4);
        employeeDatabase.addEmployee(emp5);
    }

    private HBox createPerformanceFilterPane(ObservableList<Employee<UUID>> employeeList) {
        TextField performanceField = new TextField();
        performanceField.setPromptText("Enter Performance");

        ChoiceBox<String> conditionBox = new ChoiceBox<>();
        conditionBox.getItems().addAll("Greater Than", "Less Than");
        conditionBox.setValue("Greater Than");

        Button applyFilterBtn = new Button("Filter");
        applyFilterBtn.setOnAction(e -> {
            try {
                String selectedCondition = conditionBox.getValue();
                boolean greaterThan = selectedCondition.equals("Greater Than");
                double performance = Double.parseDouble(performanceField.getText());

                List<Employee<UUID>> filtered = employeeDatabase.filterByPerformance(greaterThan, performance);
                employeeList.setAll(filtered);
                performanceField.setText("");
            } catch (NumberFormatException ex) {
                showAlert("Invalid Input", "Please enter a valid number for performance.");
            } catch (Exception ex) {
                showAlert("Error", ex.getMessage());
            }
        });

        HBox filterBox = new HBox(10, new Label("Performance Filter:"), performanceField, conditionBox, applyFilterBtn);
        filterBox.setPadding(new Insets(5));
        return filterBox;
    }

    private TextField createSearchField(ObservableList<Employee<UUID>> employeeList) {
        TextField searchField = new TextField();
        searchField.setPromptText("Search by name or department");

        searchField.textProperty().addListener((obs, oldText, newText) -> {
            try {
                if (newText.isBlank()) {
                    employeeList.setAll(employeeDatabase.getAllEmployees());
                } else {
                    ArrayList<Employee<UUID>> results = employeeDatabase.search(newText);
                    employeeList.setAll(results);
                }
            } catch (EmployeeNotFoundException e) {
                employeeList.clear();
                showAlert("No results", e.getMessage());
            } catch (Exception e) {
                showAlert("Error", e.getMessage());
            }
        });

        return searchField;
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

                employeeDatabase.addEmployee(new Employee<UUID>(name, department, salary, performance, experience));

                refreshEmployeeList();


                nameField.clear();
                deptField.clear();
                salaryField.clear();
                performanceField.clear();
                yearsOfExperienceField.clear();
            } catch (NumberFormatException ex) {
                showAlert("Error", "Please enter a valid value");
            } catch (Exception ex) {
                showAlert("Error", ex.getMessage());
            } finally {
                System.out.println("continue");
            }
        });

        form.add(addButton, 1, 5);
        return form;
    }

    private GridPane createUpdateForm() {
        GridPane form = createFormLayout();

        TextField employeeIDField = new TextField();
        TextField nameField = new TextField();
        TextField departmentField = new TextField();
        TextField salaryField = new TextField();
        TextField perfRatingField = new TextField();
        TextField yearField = new TextField();

        addFormFields(form, employeeIDField, nameField, departmentField, salaryField, perfRatingField, yearField);

        Button updateButton = new Button("Update");
        updateButton.setOnAction(e -> handleUpdate(
                employeeIDField, nameField, departmentField,
                salaryField, perfRatingField, yearField
        ));

        form.add(updateButton, 1, 6);
        return form;
    }

    private GridPane createFormLayout() {
        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);
        form.setPadding(new Insets(10));
        return form;
    }

    private void addFormFields(GridPane form, TextField employeeIDField, TextField nameField,
                               TextField departmentField, TextField salaryField,
                               TextField perfRatingField, TextField yearField) {
        form.add(new Label("Employee ID:"), 0, 0);
        form.add(employeeIDField, 1, 0);
        form.add(new Label("Name:"), 0, 1);
        form.add(nameField, 1, 1);
        form.add(new Label("Department:"), 0, 2);
        form.add(departmentField, 1, 2);
        form.add(new Label("Salary:"), 0, 3);
        form.add(salaryField, 1, 3);
        form.add(new Label("Performance Rating:"), 0, 4);
        form.add(perfRatingField, 1, 4);
        form.add(new Label("Years of Experience:"), 0, 5);
        form.add(yearField, 1, 5);
    }

    private void handleUpdate(TextField employeeIDField, TextField nameField,
                              TextField departmentField, TextField salaryField,
                              TextField perfRatingField, TextField yearField) {
        try {
            UUID ID = UUID.fromString(employeeIDField.getText());

            String name = nameField.getText();
            String department = departmentField.getText();
            String salaryText = salaryField.getText();
            String perfText = perfRatingField.getText();
            String yearText = yearField.getText();

            if (allFieldsAreBlank(name, department, salaryText, perfText, yearText)) {
                throw new IllegalArgumentException("At least one field must be filled to update employee details.");
            }

            Employee<UUID> employeeToUpdate = employeeDatabase.getEmployee(ID);
            if (employeeToUpdate == null) throw new EmployeeNotFoundException("User not found");

            updateFields(ID, name, department, salaryText, perfText, yearText);
            refreshEmployeeList();
            clearFields(employeeIDField, nameField, departmentField, salaryField, perfRatingField, yearField);

        } catch (EmployeeNotFoundException ex) {
            showAlert("Error", ex.getMessage());
        } catch (Exception ex) {
            showAlert("Error", "Invalid input: " + ex.getMessage());
        }
    }

    private boolean allFieldsAreBlank(String name, String department, String salary, String performance, String year) {
        return name.isBlank() && department.isBlank()
                && salary.isBlank() && performance.isBlank() && year.isBlank();
    }

    private void updateFields(UUID ID, String name, String department,
                              String salaryText, String perfText, String yearText) throws Exception {
        if (!name.isBlank()) {
            employeeDatabase.updateEmployeeDetail(ID, "name", name);
        }
        if (!department.isBlank()) {
            employeeDatabase.updateEmployeeDetail(ID, "department", department);
        }
        if (!salaryText.isBlank()) {
            double salary = Double.parseDouble(salaryText);
            employeeDatabase.updateEmployeeDetail(ID, "salary", salary);
        }
        if (!perfText.isBlank()) {
            double performance = Double.parseDouble(perfText);
            employeeDatabase.updateEmployeeDetail(ID, "performanceRating", performance);
        }
        if (!yearText.isBlank()) {
            int experience = Integer.parseInt(yearText);
            employeeDatabase.updateEmployeeDetail(ID, "yearsOfExperience", experience);
        }
    }

    private void clearFields(TextField... fields) {
        for (TextField field : fields) {
            field.clear();
        }
    }



    private HBox createActionButtons(TableView<Employee<UUID>> table) {
        HBox box = new HBox(10);
        box.setPadding(new Insets(10));

        Button removeButton = new Button("Remove Selected");
        removeButton.setOnAction(e -> {
            Employee<UUID> selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                try {
                    employeeDatabase.removeEmployee(selected.getEmployeeID());
                    refreshEmployeeList();
                } catch (Exception ex) {
                    showAlert("Error", ex.getMessage());
                }
            } else {
                showAlert("Error", "No employee selected");
            }
        });


        Button sortSalaryButton = new Button("Sort by salary");
        sortSalaryButton.setOnAction(e -> {
            Collections.sort(employeeList, employeeDatabase.bySalary);
        });

        Button sortPerformanceButton = new Button("Sort by Performance");
        sortPerformanceButton.setOnAction(e -> {
            Collections.sort(employeeList, employeeDatabase.byPerformance);
        });

        box.getChildren().addAll(removeButton, sortSalaryButton, sortPerformanceButton);
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