FROM java:8-alpine

ADD /job-service/target/job-service-*.jar job-service.jar

EXPOSE 8080

RUN echo 'Asia/Shanghai' > /etc/timezone

ENTRYPOINT ["java","-jar","/job-service.jar"]