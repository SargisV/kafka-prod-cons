package com.kafkaProdCons.purchaseNotifier.kafka.message;


import java.math.BigDecimal;
import java.time.Instant;

public class TicketPurchaseMessage {
    private String number;
    private String flightNumber;
    private Instant dateOfPurchase;
    private BigDecimal price;

    public TicketPurchaseMessage(String number, String flightNumber, Instant dateOfPurchase, BigDecimal price) {
        this.number = number;
        this.flightNumber = flightNumber;
        this.dateOfPurchase = dateOfPurchase;
        this.price = price;
    }

    public String getNumber() {
        return number;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public Instant getDateOfPurchase() {
        return dateOfPurchase;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "TicketPurchaseMessage{" +
                "number='" + number + '\'' +
                ", flightNumber='" + flightNumber + '\'' +
                ", dateOfPurchase=" + dateOfPurchase +
                ", price=" + price +
                '}';
    }
}
