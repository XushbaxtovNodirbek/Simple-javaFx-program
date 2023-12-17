package com.example.toourapplication.models;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Date;

public class Ticket {
    private final StringProperty country;
    private final StringProperty ticketType;
    private final Long price;
    private final Long id;
    private final ObjectProperty<Date> flightTime;
    private final ObjectProperty<Date> landingTime;
    private final boolean fromUzb;

    public Ticket(Long id, String country, String ticketType, Long price, Date flightTime, Date landingTime, boolean fromUzb) {
        this.id = id;
        this.country = new SimpleStringProperty(country);
        this.ticketType = new SimpleStringProperty(ticketType);
        this.price = (price);
        this.flightTime = new SimpleObjectProperty<>(flightTime);
        this.landingTime = new SimpleObjectProperty<>(landingTime);
        this.fromUzb = fromUzb;
    }

    public boolean isFromUzb() {
        return fromUzb;
    }

    public StringProperty countryProperty() {
        return country;
    }

    public StringProperty ticketTypeProperty() {
        return ticketType;
    }

    public ObjectProperty<Long> priceProperty() {
        return new SimpleObjectProperty<>(price);
    }

    public ObjectProperty<Long> idProp() {
        return new SimpleObjectProperty<>(id);
    }

    public ObjectProperty<Date> flightTimeProperty() {
        return flightTime;
    }

    public ObjectProperty<Date> landingTimeProperty() {
        return landingTime;
    }
}

