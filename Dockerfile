FROM openjdk:8-jdk-alpine
EXPOSE 8083
ADD target/GesF-1.0.jar GesF-1.0.jar
COPY . .
ENTRYPOINT ["java","-jar","/GesF-1.0.jar"]

