FROM openjdk:17
EXPOSE 8081
ADD target/rest.jar rest.jar
ENTRYPOINT ["java","-jar","/rest.jar"]