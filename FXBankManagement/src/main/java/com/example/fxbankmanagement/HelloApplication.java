package com.example.fxbankmanagement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {



    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws  Exception {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/src/application/Main.fxml"));
            Scene scene = new Scene(root, 400, 400);

            stage.setScene(scene);
            stage.show();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}