package com.kafkaProdCons.skyscannerPurchaseListener.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

public class TicketPurchaseMessage {
    @Id
    private UUID id;
    private String data;

    public TicketPurchaseMessage(String data) {
        this.id = UUID.randomUUID();
        this.data = data;
    }

    public UUID getId() {
        return id;
    }

    public String getData() {
        return data;
    }


}
