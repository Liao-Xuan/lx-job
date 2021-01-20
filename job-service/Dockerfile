FROM registry.cn-chengdu.aliyuncs.com/zbox/jre:1.8

WORKDIR /opt/

RUN echo "Asia/Shanghai" > /etc/timezone

RUN mkdir /data

USER root

EXPOSE 8081

ADD target/job-service*.jar app.jar

CMD ["java","-jar","/opt/app.jar"]