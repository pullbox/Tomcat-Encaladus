# Building Dockerfile with Tomcat 9 and deploying the tomcat-orion war file
FROM tomcat:9.0

MAINTAINER Daniel Bechtel

ENV TAKIPI_COLLECTOR_PORT=6060
ENV TAKIPI_COLLECTOR_HOST=172.20.48.78
ENV TAKIPI_SERVER_NAME=encaladus-container

# COPY path to the war     path to the webapps in docker tomcat

COPY ../target/Enceladus-1.0.war /usr/local/tomcat/webapps/


workdir /
RUN wget -qO- https://s3.amazonaws.com/app-takipi-com/deploy/linux/takipi-agent-latest.tar.gz | tar -xz

COPY setenv.sh /usr/local/tomcat/bin/
#create setenv.sh file
RUN chmod +x /usr/local/tomcat/bin/setenv.sh

EXPOSE 8080

CMD ["catalina.sh", "run"]
