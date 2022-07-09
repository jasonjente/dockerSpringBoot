FROM adoptopenjdk/openjdk11:x86_64-alpine-jdk-11.0.14.1_1-slim

ENV JAR_FILE=./springboot/target/SimpleApplication-1.0.0.0.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]