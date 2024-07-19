package com.kafkaProdCons.purchaseNotifier.kafka.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConstant {
    public static final String PURCHASE_TOPIC = "ticket.purchases";
}
