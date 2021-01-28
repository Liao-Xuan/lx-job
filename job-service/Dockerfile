FROM openjdk:8-jdk-alpine

VOLUME /tmp

ADD /target/job-service-*.jar job-service.jar

ENTRYPOINT ["java","-jar","/job-service.jar"]