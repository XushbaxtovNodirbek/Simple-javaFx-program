package com.example.toourapplication.controllers;

import com.example.toourapplication.services.AdminServices;
import com.example.toourapplication.services.ClientService;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MainController {
    @FXML
    AnchorPane mainPane;


    @FXML
    protected void onAdminButton() throws IOException {
        AdminServices.openAdminLoginForm(mainPane);
    }

    @FXML
    protected void onClientButton() throws IOException {
        ClientService.openClientLoginForm(mainPane);
    }
}