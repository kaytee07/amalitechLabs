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
    private Label name;

    @FXML
    private Label deposit;

    @FXML
    private Button handleWithdraw;

    @FXML
    private Button handleDeposit;

    @FXML
    private TableView<Transaction> transactionTable; // The TableView to display transactions

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
        String withdrawText = withDrawValue.getText();

        if (withdrawText.isEmpty()) {
          showAlert(AlertType.WARNING, "Input Error", "Please enter a withdrawal amount.");
            return;
        }

        try {
            double withdrawAmount = Double.parseDouble(withdrawText);
            if (withdrawAmount <= 0) {
                showAlert(AlertType.WARNING, "Input Error", "Withdrawal amount must be positive.");
                return;
            }
            account.withdraw(withdrawAmount);
            balance = account.getBalance();
            deposit.setText("Balance: $" + balance.toString());
            withDrawValue.setText("");
            System.out.println(balance);
        } catch (NumberFormatException e){
            showAlert(AlertType.ERROR, "Invalid Amount", "Please enter a valid numeric amount.");
        }

    }

    @FXML
    public void handleDeposit(ActionEvent event) throws Exception {
        String depositText = depositValue.getText();

        if (depositText.isEmpty()) {
            showAlert(AlertType.WARNING, "Input Error", "Please enter a withdrawal amount.");
            return;
        }

        try {
            double depositAmount = Double.parseDouble(depositText);
            if (depositAmount <= 0) {
                showAlert(AlertType.WARNING, "Input Error", "Withdrawal amount must be positive.");
                return;
            }
            account.deposit(depositAmount);
            balance = account.getBalance();
            deposit.setText("Balance: $" + balance.toString());
            withDrawValue.setText("");
            System.out.println(balance);
        } catch (NumberFormatException e){
            showAlert(AlertType.ERROR, "Invalid Amount", "Please enter a valid numeric amount.");
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





