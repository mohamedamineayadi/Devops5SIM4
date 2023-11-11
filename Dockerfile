FROM openjdk:11-jre-slim
COPY target/devopsamine-1.0.jar app.jar
EXPOSE 8085
CMD ["java", "-jar", "app.jar"]