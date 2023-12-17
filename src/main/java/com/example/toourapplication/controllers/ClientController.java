package com.example.toourapplication.controllers;

import com.example.toourapplication.models.Client;
import com.example.toourapplication.models.ClientDTO;
import com.example.toourapplication.models.DbConnection;
import com.example.toourapplication.services.ClientService;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ClientController {
    @FXML
    AnchorPane main_pane;
    @FXML
    TextField phone,username,email,pass_id,lastName,firstName;
    @FXML
    PasswordField password;
    @FXML
    Label validation;

    @FXML
    protected void onExit() throws IOException {
        ClientService.exitToMainPage(main_pane);
    }

    @FXML
    protected void openClientRegister() throws IOException {
        ClientService.openRegisterForm(main_pane);
    }

    @FXML
    protected void onSubmit(){
        if (username.getText().isEmpty() || password.getText().isEmpty() || firstName.getText().isEmpty() || lastName.getText().isEmpty() || pass_id.getText().isEmpty() || email.getText().isEmpty() || phone.getText().isEmpty())
            validation.setText("All filed is required!");
        else {
            Platform.runLater(()->{
                DbConnection.saveClient(
                    new ClientDTO(
                            lastName.getText(),
                            firstName.getText(),
                            email.getText(),
                            phone.getText(),
                            username.getText(),
                            pass_id.getText(),
                            password.getText()
                    )
                );
                try {
                    ClientService.openWorkSpace(main_pane,firstName.getText(),lastName.getText(),username.getText());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    @FXML
    protected void onLogin(){
        if (username.getText().isEmpty() || password.getText().isEmpty())
            validation.setText("All filed is required!");
        else {
            Platform.runLater(()->{
                Client client = DbConnection.getClient(username.getText());
                if (client == null || !client.getPassword().equals(password.getText()))
                    validation.setText("Incorrect username or password");
                else{
                    try {
                        ClientService.openWorkSpace(main_pane,client.getFname(),client.getLname(), client.getUsername());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
    }
}
