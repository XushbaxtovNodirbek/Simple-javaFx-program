package com.example.toourapplication.controllers;

import com.example.toourapplication.models.Admin;
import com.example.toourapplication.models.DbConnection;
import com.example.toourapplication.services.AdminServices;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class AdminController {
    @FXML
    AnchorPane main_pane;
    @FXML
    Label validation;
    @FXML
    TextField username;
    @FXML
    PasswordField password;

    @FXML
    protected void onExit() throws IOException {
        AdminServices.exitToMainPage(main_pane);
    }
    @FXML
    protected void onLogin(){
        Platform.runLater(() -> {
            if (username.getText().isEmpty() || password.getText().isEmpty())
                validation.setText("All filed is required!");
            else {
                Admin admin = DbConnection.getAdmin(username.getText());
                if (admin == null || !admin.getPassword().equals(password.getText())){
                    validation.setText("Incorrect username or password!");
                }else {
                    try {
                        AdminServices.openAdminWorkSpace(main_pane);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

}
