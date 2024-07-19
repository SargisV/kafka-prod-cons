package com.kafkaProdCons.skyscannerPurchaseListener.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.bson.UuidRepresentation;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

import java.util.Collection;
import java.util.Collections;

@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {
    private static final String DATABASE_NAME = "skyscanner_db";
    @Value("${mongo.connection-uri}")
    private String connectionUri;

    @Override
    public String getDatabaseName() {
        return DATABASE_NAME;
    }

    @Bean
    @Override
    public MongoClient mongoClient() {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        ConnectionString connectionString = new ConnectionString(String.format("%s/%s", connectionUri, DATABASE_NAME));
        MongoClientSettings settings = MongoClientSettings.builder()
                .codecRegistry(CodecRegistries.fromRegistries(
                        MongoClientSettings.getDefaultCodecRegistry(),
                        CodecRegistries.fromProviders(pojoCodecProvider)))
                .applyConnectionString(connectionString)
                .uuidRepresentation(UuidRepresentation.STANDARD)
                .build();

        return MongoClients.create(settings);
    }

    @Override
    public Collection<String> getMappingBasePackages() {
        return Collections.singleton("com.kafkaProdCons.skyscannerPurchaseListener.document");
    }
}
