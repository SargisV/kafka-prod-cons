FROM maven:3.8.6-openjdk-11 AS build

WORKDIR /app

COPY pom.xml ./
COPY src ./src

RUN mvn clean package -DskipTests

FROM openjdk:11-jdk-slim

WORKDIR /app

COPY --from=build /app/target/purchaseNotifier-0.0.1-SNAPSHOT.jar /app/purchaseNotifier.jar

EXPOSE 5000

ENTRYPOINT ["java", "-jar", "purchaseNotifier.jar"]
