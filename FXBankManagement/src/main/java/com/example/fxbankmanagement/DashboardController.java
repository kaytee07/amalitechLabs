package com.example.fxbankmanagement;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
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
    private TableView transactionTable;

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
    private TableColumn<Transaction, String> dateColumn; // Column for Date

    @FXML
    private TableColumn<Transaction, String> typeColumn; // Column for Type

    @FXML
    private TableColumn<Transaction, Double> amountColumn; // Column for Amount


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
            transactionTable.getItems().setAll(account.getLastNHistory(2));
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

            transactionTable.getItems().setAll(account.getLastNHistory(2));

            deposit.setText("Balance: $" + balance);

            depositValue.setText("");  // Assuming depositValue is where the user inputs the deposit amount

        } catch (Exception e) {
            // Handle any errors and display the message on the error label
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





