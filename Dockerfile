FROM openjdk:8-jre-alpine
ADD target/agamotto-xxx.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar","--spring.profiles.active=pro"]
