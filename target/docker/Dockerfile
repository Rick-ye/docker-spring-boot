FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD docker-spring-boot-1.0.0.jar app.jar
ADD apm-all-2.0.0.jar /usr/local/apm-all-2.0.0.jar
ADD apm.properties /usr/local/apm.properties
CMD java -javaagent:/usr/local/apm-all-2.0.0.jar -jar app.jar