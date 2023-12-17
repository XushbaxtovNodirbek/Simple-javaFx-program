module com.example.toourapplication {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.postgresql.jdbc;
    requires java.sql;


    opens com.example.toourapplication to javafx.fxml;
    exports com.example.toourapplication;
    exports com.example.toourapplication.controllers;
    opens com.example.toourapplication.controllers to javafx.fxml;
}