FROM openjdk:11-jre-slim
COPY target/devops-project-1.0.jar app.jar
EXPOSE 8082
CMD ["java", "-jar", "app.jar"]