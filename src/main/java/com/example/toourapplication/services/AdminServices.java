package com.example.toourapplication.services;

import com.example.toourapplication.TourApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminServices {
    public static void openAdminLoginForm(AnchorPane mainPane) throws IOException {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(TourApplication.class.getResource("AdminLoginForm.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),600,400);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Admin Login Form");
        stage.show();
    }
    public static void openAdminWorkSpace(AnchorPane mainPane) throws IOException {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(TourApplication.class.getResource("AdminWorkSpace.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),1000,600);
        stage.setResizable(false);
        stage.setTitle("WorkSpace");
        stage.setScene(scene);
        stage.show();
    }
    public static void exitToMainPage(AnchorPane mainPane) throws IOException {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(TourApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600,400);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("DREAM TOUR");
        stage.show();
    }

    public static void openAddForm() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(TourApplication.class.getResource("AddForm.fxml"));
        Scene scene = new Scene(loader.load(),400,500);
        stage.setTitle("Add Ticket");
        stage.setScene(scene);
        stage.show();
    }
}
