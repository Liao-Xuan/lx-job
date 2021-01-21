FROM maven:3.5.4-alpine

ADD . /app

WORKDIR /app/

RUN mvn clean package

EXPOSE 8080

ADD target/job-service*.jar app.jar

CMD ["java","-jar","/opt/app.jar"]