package com.kafkaProdCons.skyscannerPurchaseListener.kafka.consumer;

import com.mongodb.client.MongoClient;
import com.kafkaProdCons.skyscannerPurchaseListener.config.KafkaConstant;
import com.kafkaProdCons.skyscannerPurchaseListener.config.MongoConfig;
import com.kafkaProdCons.skyscannerPurchaseListener.document.TicketPurchaseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TicketPurchaseConsumer {
    private static final String KAFKA_MESSAGES_COLLECTION = "kafka_messages";
    private static final Logger logger = LoggerFactory.getLogger(TicketPurchaseConsumer.class);
    private MongoConfig mongoConfig;
    private MongoClient mongoClient;

    public TicketPurchaseConsumer(MongoConfig mongoConfig, MongoClient mongoClient) {
        this.mongoConfig = mongoConfig;
        this.mongoClient = mongoClient;
    }

    @KafkaListener(topics = KafkaConstant.PURCHASE_TOPIC, groupId = KafkaConstant.DEFAULT_GROUP_ID)
    public void listen(String message) {
        logger.info("Receieved message from kafka: {}", message);
        TicketPurchaseMessage ticketPurchaseMessage;
        try {
            ticketPurchaseMessage = new TicketPurchaseMessage(message);
            logger.info("Retrieved ticket purchase message");
        } catch (Exception e) {
            logger.error("Could not retrieved ticket purchase message {}", e.getMessage());
            return;
        }
        try {
            mongoClient.getDatabase(mongoConfig.getDatabaseName())
                    .getCollection(KAFKA_MESSAGES_COLLECTION, TicketPurchaseMessage.class)
                    .insertOne(ticketPurchaseMessage);
            logger.info("Saved ticket purchase message to database");
        } catch (Exception e) {
            logger.error("Could not save ticket purchase message to database {}", e.getMessage());
        }
    }
}
