FROM openjdk:11.0.11-9-jdk
COPY target/personapi-0.0.1-SNAPSHOT.jar personapi-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/personapi-0.0.1-SNAPSHOT.jar"]