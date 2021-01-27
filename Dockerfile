FROM maven:3.6.3-jdk-8-alpine AS MAVEN_BUILD

ADD job-service-*.jar job-service.jar

EXPOSE 8001

ENTRYPOINT ["java","-jar","/job-service.jar"]