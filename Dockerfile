FROM openjdk:19-alpine
ADD target/cybernateBack-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]