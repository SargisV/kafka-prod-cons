FROM maven:3.8.6-openjdk-11 AS build

WORKDIR /app

COPY pom.xml ./
COPY src ./src

RUN mvn clean package -DskipTests

FROM openjdk:11-jdk-slim

WORKDIR /app

COPY --from=build /app/target/skyscannerPurchaseListener-0.0.1-SNAPSHOT.jar /app/skyscannerPurchaseListener.jar

EXPOSE 5001

ENTRYPOINT ["java", "-jar", "skyscannerPurchaseListener.jar"]
