package com.example.fxbankmanagement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;


public class Controller {
    @FXML
    private TextField username;

    @FXML
    private TextField deposit;

    @FXML
    private ComboBox<String> accountType;

    @FXML
    private Button submit;

    @FXML
    private AnchorPane mainPane;

    public void handleSubmit(ActionEvent e) throws IOException {
        String name = username.getText();

        String receivedCash  = deposit.getText();

        String typeOfAccount = accountType.getValue();

        Account account = null;

        User user = new User(name);

        switch (typeOfAccount){
            case "Savings Account" -> {
                if (receivedCash != null && !receivedCash.trim().isEmpty()){
                    account = new SavingsAccount(user, Double.parseDouble(receivedCash));
                } else {
                    account = new SavingsAccount(user);
                }
                
            }
            case "Current Account" -> {
                if (receivedCash != null && !receivedCash.trim().isEmpty()){
                    account = new CurrentAccount(user, Double.parseDouble(receivedCash));
                } else {
                    account = new CurrentAccount(user);
                }
            }
            case "Fixed Deposit" -> {
                if (receivedCash != null && !receivedCash.trim().isEmpty()){
                    account = new FixedDepositAccount(user,Double.parseDouble(receivedCash));
                } else {
                    Alert alert = new Alert  (Alert.AlertType.WARNING);
                    alert.setTitle("Error");
                    alert.setHeaderText("Deposit Required");
                    alert.setContentText("You need to make a fixed deposit");
                } 
                
            }
        }

        System.out.println("Name" + user);
        System.out.println("deposit " + receivedCash);


        FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
        Parent root = loader.load();
        DashboardController dashboardController = loader.getController();
        dashboardController.acceptData(user, account, receivedCash);

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();



    }
}
