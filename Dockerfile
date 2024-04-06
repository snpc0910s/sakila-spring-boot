FROM openjdk:8-jdk-alpine
WORKDIR /app
COPY target/sakila-demo-1.0.0.jar sakila-demo-1.0.0.jar
CMD ["java","-jar","sakila-demo-1.0.0.jar"]
