FROM maven:3.6.1-jdk-11-slim as builder
COPY . /usr/src/mymaven
WORKDIR /usr/src/mymaven
RUN mvn clean install -f /usr/src/mymaven && mkdir /usr/src/wars/
RUN find /usr/src/mymaven/ -iname '*.war' -exec cp {} /usr/src/wars/ \;

FROM tomcat:9.0.21-jdk11-openjdk
COPY tomcat-users.xml  $CATALINA_HOME/conf/
COPY --from=builder /usr/src/wars/* /usr/local/tomcat/webapps/