FROM java:8-alpine

ADD job-service-*.jar job-service.jar

EXPOSE 8001

ENTRYPOINT ["java","-jar","/job-service.jar"]