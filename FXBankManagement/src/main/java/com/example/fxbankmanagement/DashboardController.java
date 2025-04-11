package com.example.fxbankmanagement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.event.ActionEvent;



public class DashboardController {
    public TextField depositValue;
    public  TextField withDrawValue;

    @FXML
    private TextField transactionsField;

    @FXML
    private Button transactionButton;

    @FXML
    private TableView<Transaction> transactionTable;

    @FXML
    private Label errorLabel;
    @FXML
    private Label name;

    @FXML
    private Label deposit;

    @FXML
    private Button handleWithdraw;

    @FXML
    private Button handleDeposit;


    @FXML
    private TableColumn<Transaction, Double> valueColumn;


    @FXML
    private TableColumn<Transaction, String> transactionTypeColumn;

    @FXML
    private TableColumn<Transaction, String> dateColumn;

    @FXML
    public void initialize() {
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
        transactionTypeColumn.setCellValueFactory(new PropertyValueFactory<>("transactionType"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("Date"));
    }


    String username;
    Double balance;
    Account account;





    public void acceptData(User user, Account newAccount, String value) {
        username = user.getUsername();
        account = newAccount;
        balance = account.getBalance();


        updateLabels();
    }

    void updateLabels() {
        if (name != null && deposit != null) {
            name.setText("Welcome, " + username);
            deposit.setText("Balance: $" + balance.toString());
        } else {
            System.out.println("Labels are not initialized properly.");
        }
    }

    @FXML
    public void handleWithdraw(ActionEvent event) throws Exception {

        /**/


        try {
            String withdrawText = withDrawValue.getText();

            if (withdrawText.isEmpty()) {
                throw new Exception("Please enter a withdrawal amount.");
            }

            double withdrawAmount = Double.parseDouble(withdrawText);

            if (withdrawAmount <= 0) {
                throw  new Exception ("amount to withdraw must be greater than 0");
            }
//            transactionTable.getItems().setAll(account.transactionHistory.getNHistory(2));
            account.withdraw(withdrawAmount);
            balance = account.getBalance();
            deposit.setText("Balance: $" + balance.toString());
            withDrawValue.setText("");
            System.out.println(balance);
        } catch (Exception e){
            errorLabel.setText(e.getMessage());
        }

    }

    @FXML
    public void handleDeposit(ActionEvent event) throws Exception {

        try {
            String depositText = depositValue.getText();  // Assuming depositValue is the correct input field for deposit

            if (depositText.isEmpty()) {
                throw new Exception("Please enter a deposit amount.");
            }

            double depositAmount = Double.parseDouble(depositText);

            if (depositAmount <= 0) {
                throw new Exception("Deposit amount must be positive.");
            }

            account.deposit(depositAmount);

            balance = account.getBalance();

//            transactionTable.getItems().setAll(account.transactionHistory.getNHistory(2));

            deposit.setText("Balance: $" + balance);

            depositValue.setText("");

        } catch (Exception e) {
            // Handle any errors and display the message on the error label
            errorLabel.setText(e.getMessage());
        }

    }

    @FXML
    public void handleGetNHistory (ActionEvent event) throws Exception {
        try {
            int N = Integer.parseInt(transactionsField.getText());
            Transaction[] history = account.transactionHistory.getNHistory(N);

            ObservableList<Transaction> transactions = FXCollections.observableArrayList(history);
            transactionTable.setItems(transactions);

        } catch (NumberFormatException e) {
            errorLabel.setText(e.getMessage());
        } catch (Exception e){
            errorLabel.setText(e.getMessage());
        }
    }

    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


}





