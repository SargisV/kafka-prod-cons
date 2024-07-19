# Kafka-MongoDB-Dockerized Application

This project simulates multiple plane ticket purchase systems. Using the example of Kayak and SkyScanner services it demonstrates how to receive JSON messages from Kafka and write them to a MongoDB database. The idea is to notify Kayak and SkyScanner when a ticket is sold.

## Prerequisites

- **Docker** installed
- Available ports: 5000, 5001, 5002, 9092, 2181, 27017, 8081

## Getting Started

1. **Build Docker images**:
    ```bash
    docker-compose build
    ```

2. **Start containers**:
    ```bash
    docker-compose up
    ```

3. **Monitor MongoDB**:
   Visit `http://localhost:8081` with credentials:
    - Username: `admin`
    - Password: `pass`

NOTE: Please wait 30 seconds for databases to appear in Mongo Express. 

## Services

- **Kafka & Zookeeper**: Kafka handles messaging; Zookeeper manages Kafka brokers.
- **PurchaseNotifier**: Generates and sends ticket sale messages to Kafka.
- **KayakPurchaseListener**: Consumes messages from Kafka and stores them in MongoDB (`kayak_db`).
- **SkyScannerPurchaseListener**: Similar to Kayak it stores messages in MongoDB (`skyscanner_db`).
- **MongoDB**: Database Server for each application(Kayak and SkyScanner) to have its own database.
- **Mongo Express**: Web client to visualize MongoDB data.
