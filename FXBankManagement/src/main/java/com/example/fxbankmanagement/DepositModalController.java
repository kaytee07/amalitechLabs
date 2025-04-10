package com.example.fxbankmanagement;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DepositModalController {

    double amountDeposited = 0.0;
    private Account account;

    @FXML
    private TextField amountField;

    private DashboardController dashboardController;

    public void setDashboardController(DashboardController controller) {
        this.dashboardController = controller;
    }

    @FXML
    private void handleDeposit() {
        try {
            double depositAmount = Double.parseDouble(amountField.getText());
            if (depositAmount > 0) {
                amountDeposited = depositAmount;
            }

            if (dashboardController != null) {
                dashboardController.balance = account.getBalance(); // Update balance
                dashboardController.updateLabels(); // Update the balance on the UI
            }

        } catch (NumberFormatException e) {
            // Handle invalid input (non-numeric value)
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Input");
            alert.setHeaderText("Invalid Amount");
            alert.setContentText("Please enter a valid numeric amount.");
            alert.showAndWait();
        }
    }

    // Handle Cancel action
    @FXML
    private void handleCancel() {
        ((Stage) amountField.getScene().getWindow()).close();  // Close modal without action
    }

    public void acceptData(Account getAccount) {
        this.account = getAccount;
    }


}

