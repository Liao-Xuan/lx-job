FROM maven:3.5.4-alpine

ADD . /app

WORKDIR /app/

RUN mvn clean package

EXPOSE 8080

ENTRYPOINT java -Djava.security.egd=file:/dev/./urandom -jar target/job-service-1.0.0.jar