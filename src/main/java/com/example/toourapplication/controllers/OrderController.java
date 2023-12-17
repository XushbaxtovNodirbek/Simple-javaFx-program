package com.example.toourapplication.controllers;

import com.example.toourapplication.services.ClientService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class OrderController {
    public AnchorPane main;
    @FXML
    private Label flight;

    @FXML
    private Label from;

    @FXML
    private Label fullname;

    @FXML
    private Label land;

    @FXML
    private Label passid;

    @FXML
    private Label price;

    @FXML
    private Label ticetid;

    @FXML
    private Label to;

    public void init(String flight,String from,String to,String land,String price,String fullname,String passid,String ticketid){
        this.flight.setText(flight);
        this.from.setText(from);
        this.to.setText(to);
        this.land.setText(land);
        this.price.setText(price+"$");
        this.fullname.setText(fullname);
        this.passid.setText(passid);
        this.ticetid.setText("ID"+ticketid);
    }

    @FXML
    void onBuy(ActionEvent event) {
        ClientService.orderSuccess(main);
    }
}
