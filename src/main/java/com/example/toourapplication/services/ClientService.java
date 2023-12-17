package com.example.toourapplication.services;

import com.example.toourapplication.TourApplication;
import com.example.toourapplication.controllers.ClientWorkSpaceController;
import com.example.toourapplication.controllers.OrderController;
import com.example.toourapplication.models.Client;
import com.example.toourapplication.models.DbConnection;
import com.example.toourapplication.models.Ticket;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.DataFormat;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class ClientService {
    public static void exitToMainPage(AnchorPane mainPane) throws IOException {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(TourApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600,400);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("DREAM TOUR");
        stage.show();
    }
    public static void openClientLoginForm(AnchorPane mainPane) throws IOException {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(TourApplication.class.getResource("ClientLoginForm.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),600,400);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("Client Login Form");
        stage.show();
    }
    public  static void openRegisterForm(AnchorPane mainPane) throws IOException {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(TourApplication.class.getResource("ClientRegisterForm.fxml"));
        Scene scene = new Scene(loader.load(),600,400);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("Client Register");
        stage.show();
    }
    public static void openWorkSpace(AnchorPane mainPane,String firstName,String lastName,String username) throws IOException {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(TourApplication.class.getResource("ClientWorkSpace.fxml"));
        Parent root = loader.load();

        // Get the controller from the FXMLLoader
        ClientWorkSpaceController controller = loader.getController();

        Scene scene = new Scene(root, 1000, 500);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("Client Work Space");
        stage.show();

        controller.init(firstName,lastName,username);
    }

    public static void openOrderPage(Ticket ticket, String username) {
        Platform.runLater(()->{
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(TourApplication.class.getResource("OrderPage.fxml"));
            try {
                Parent root = loader.load();
                OrderController controller = loader.getController();
                Scene scene = new Scene(root, 842, 400);
                stage.setResizable(false);
                stage.setScene(scene);
                stage.setTitle("Order Page");
                stage.show();
                Client client = DbConnection.getClient(username);
                SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd");
                controller.init(
                        dateFormat.format(ticket.flightTimeProperty().getValue()),
                        ticket.isFromUzb() ? "Uzbekistan":ticket.countryProperty().getValue(),
                        ticket.isFromUzb() ? ticket.countryProperty().getValue():"Uzbekistan",
                        dateFormat.format(ticket.landingTimeProperty().getValue()),
                        ticket.priceProperty().getValue().toString(),
                        client.getFname()+" "+client.getLname(),
                        client.getPassid(),ticket.idProp().getValue().toString()
                );

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static void orderSuccess(AnchorPane main) {
        Stage stage = (Stage) main.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(TourApplication.class.getResource("succesModel.fxml"));
        try {
            Scene scene = new Scene(loader.load(),400,400);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.setTitle("Success");
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
