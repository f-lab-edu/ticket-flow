FROM openjdk:17-jdk

COPY build/libs/*SNAPSHOT.jar /project.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/project.jar"]