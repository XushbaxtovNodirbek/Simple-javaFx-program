package com.example.toourapplication.controllers;

import com.example.toourapplication.models.DbConnection;
import com.example.toourapplication.models.Ticket;
import com.example.toourapplication.services.AdminServices;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class AdminWorkSpaceController implements Initializable {
    // flight from Uzb
    @FXML
    private TableColumn<Ticket, String> country;

    @FXML
    private TableColumn<Ticket,Long> id;

    @FXML
    private TableColumn<Ticket, Date> flight_time;

    @FXML
    private TableColumn<Ticket, Date > landing_time;

    @FXML
    private TableColumn<Ticket, Long> price;

    @FXML
    private TableView<Ticket> ticketTable;

    @FXML
    private TableColumn<Ticket, String> ticket_type;

    // flight to Uzb
    @FXML
    private TableColumn<Ticket, String> countryUz;

    @FXML
    private TableColumn<Ticket,Long> idUz;

    @FXML
    private TableColumn<Ticket, Date> flight_timeUz;

    @FXML
    private TableColumn<Ticket, Date > landing_timeUz;

    @FXML
    private TableColumn<Ticket, Long> priceUz;

    @FXML
    private TableView<Ticket> ticketTableUz;

    @FXML
    private TableColumn<Ticket, String> ticket_typeUz;



    @FXML
    void add(ActionEvent event) {
        Platform.runLater(()->{
            try {
                AdminServices.openAddForm();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @FXML
    void refresh(ActionEvent event) {
        ticketList.clear();
        ticketListUz.clear();
        Platform.runLater(()->{
            for (Ticket ticket : DbConnection.getTickets()) {
                if (ticket.isFromUzb()){
                    ticketList.add(ticket);
                }else {
                    ticketListUz.add(ticket);
                }
            }
        });
    }

    @FXML
    void remove(ActionEvent event) {
        int selectedIndex = ticketTable.getSelectionModel().getSelectedIndex();
        int selectedIndexUZ = ticketTableUz.getSelectionModel().getSelectedIndex();
        if (selectedIndex > -1){
            Long id = ticketList.get(selectedIndex).idProp().get();
            ticketTable.getItems().remove(selectedIndex);
            Platform.runLater(()->DbConnection.deleteTicket(id));
        }
        if (selectedIndexUZ > -1){
            Long id = ticketListUz.get(selectedIndexUZ).idProp().get();
            ticketTableUz.getItems().remove(selectedIndexUZ);
            Platform.runLater(()->DbConnection.deleteTicket(id));
        }
    }




    private ObservableList<Ticket> ticketList = FXCollections.observableArrayList();
    private ObservableList<Ticket> ticketListUz = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Set the cell value factories for each column
        country.setCellValueFactory(cellData -> cellData.getValue().countryProperty());
        flight_time.setCellValueFactory(cellData -> cellData.getValue().flightTimeProperty());
        landing_time.setCellValueFactory(cellData -> cellData.getValue().landingTimeProperty());
        price.setCellValueFactory(cellData -> cellData.getValue().priceProperty());
        ticket_type.setCellValueFactory(cellData -> cellData.getValue().ticketTypeProperty());
        id.setCellValueFactory(cellData -> cellData.getValue().idProp());
        // Set the cell value factories for each column
        countryUz.setCellValueFactory(cellData -> cellData.getValue().countryProperty());
        flight_timeUz.setCellValueFactory(cellData -> cellData.getValue().flightTimeProperty());
        landing_timeUz.setCellValueFactory(cellData -> cellData.getValue().landingTimeProperty());
        priceUz.setCellValueFactory(cellData -> cellData.getValue().priceProperty());
        idUz.setCellValueFactory(cellData -> cellData.getValue().idProp());
        ticket_typeUz.setCellValueFactory(cellData -> cellData.getValue().ticketTypeProperty());

        // Set the items of the table with the data
        ticketTable.setItems(ticketList);
        ticketTableUz.setItems(ticketListUz);

        // Add sample data (replace this with your actual data)
        Platform.runLater(()->{
            for (Ticket ticket : DbConnection.getTickets()) {
                if (ticket.isFromUzb()){
                    ticketList.add(ticket);
                }else {
                    ticketListUz.add(ticket);
                }
            }
        });
    }
}
