FROM openjdk:13-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

ARG JAR_FILE=boot/target/boot-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

