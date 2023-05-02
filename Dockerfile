FROM openjdk:19-alpine
ARG JAR_FILE=target/*.jar
COPY ./target/springboot-postgres-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]