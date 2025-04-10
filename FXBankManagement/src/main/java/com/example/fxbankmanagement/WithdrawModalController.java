package com.example.fxbankmanagement;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class WithdrawModalController {

    @FXML
    private TextField amountField;

    private DashboardController dashboardController;
    private Account account = null;
    Double amountWithDrawn = 0.0;


    public void setDashboardController(DashboardController controller) {
        this.dashboardController = controller;
    }

    @FXML
    private void handleWithdraw() {
        try {
            double withdrawAmount = Double.parseDouble(amountField.getText());
            amountWithDrawn = withdrawAmount;
        } catch (NumberFormatException e) {
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
        account = getAccount;
    }




}

