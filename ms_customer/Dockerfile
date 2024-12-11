FROM eclipse-temurin:21-jdk-jammy as build
WORKDIR /app
RUN apt-get update && apt-get install -y maven
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:21
WORKDIR /app
COPY --from=build /app/target/*.jar ./app.jar

ENV DB_URL=jdbc:postgresql://postgres:5432/mscustomer
ENV DB_USERNAME=root
ENV DB_PASSWORD=senha
ENV EUREKA_URL=http://eureka-server:8761/eureka

ENTRYPOINT ["java", "-jar", "app.jar"]
