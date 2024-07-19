package com.kafkaProdCons.purchaseNotifier.kafka.producer;

import com.kafkaProdCons.purchaseNotifier.kafka.config.KafkaConstant;
import com.kafkaProdCons.purchaseNotifier.kafka.message.TicketPurchaseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.Random;

@Service
@EnableScheduling
public class TicketPurchaseProducer {
    private KafkaTemplate<String, TicketPurchaseMessage> kafkaTemplate;
    private static final Logger logger = LoggerFactory.getLogger(TicketPurchaseProducer.class);

    public TicketPurchaseProducer(KafkaTemplate<String, TicketPurchaseMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Scheduled(cron = "*/5 * * * * *")
    public void produceEvent() {
        TicketPurchaseMessage message;
        try {
            message = generatePurchaseMessage();
            logger.info("Generated ticket purchase message: {}", message);
        } catch (Exception e) {
            logger.error("Could not generated ticket purchase message: {}", e.getMessage());
            return;
        }
        try {
            kafkaTemplate.send(KafkaConstant.PURCHASE_TOPIC, message);
            logger.info("Sent ticket purchased message to kafka: {}", message);
        } catch (Exception e) {
            logger.error("Could not send ticket purchased message to kafka: {}", e.getMessage());
        }
    }

    private TicketPurchaseMessage generatePurchaseMessage() {
        Random random = new Random();
        return new TicketPurchaseMessage(
                "Ticket-Number" + String.format("%05d", random.nextInt(100000)),
                "FL" + String.format("%04d", random.nextInt(10000)),
                Instant.now(),
                BigDecimal.valueOf(50 + (200 - 50) * random.nextDouble()).setScale(2, RoundingMode.HALF_UP)
        );
    }
}

