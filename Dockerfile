FROM maven:3.6.3-jdk-15 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

FROM openjdk:15-jdk-buster
COPY --from=build /home/app/target/*.jar /usr/local/lib/ROOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar", "/usr/local/lib/ROOT.jar"]
