FROM openjdk:11
COPY target/Springboot_postgres-0.0.1-SNAPSHOT.jar SpringMVC.jar
ENTRYPOINT ["java","-jar","/SpringMVC.jar"]