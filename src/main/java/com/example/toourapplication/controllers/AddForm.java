package com.example.toourapplication.controllers;

import com.example.toourapplication.models.DbConnection;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class AddForm implements Initializable {
    @FXML
    private Label validation;
    @FXML
    private ChoiceBox<String> choise_class;

    @FXML
    private TextField country;

    @FXML
    private DatePicker flight_time;

    @FXML
    private CheckBox from_uzb;

    @FXML
    private DatePicker landing_time;

    @FXML
    private TextField price;

    @FXML
    void submit(ActionEvent event) {
        if (country.getText().isEmpty() || price.getText().isEmpty() || landing_time.getValue() == null || flight_time.getValue() == null) validation.setText("All filed is required");
        else {
            validation.setStyle("-fx-text-fill: green");
            Platform.runLater(()->{
                DbConnection.saveTicket(
                        country.getText(),
                        choise_class.getValue(),
                        Long.valueOf(price.getText()),
                        flight_time.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                        landing_time.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                        from_uzb.isSelected()
                );
                validation.setText("Success");
            });
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choise_class.getItems().addAll("Business", "Econom", "First Class");
        choise_class.setValue("Econom");
    }
}
