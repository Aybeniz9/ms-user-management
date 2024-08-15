FROM openjdk:22-jdk-slim
COPY build/libs/profile-app-service-0.0.1-SNAPSHOT.jar user-management.jar
ENTRYPOINT ["java", "-jar", "user-management.jar"]