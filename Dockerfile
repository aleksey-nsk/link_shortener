FROM java:8-jre
MAINTAINER Aleksey Zhdanov <aleksey.zhd@gmail.com>
ADD ./target/smaller.jar /app/
CMD ["java", "-jar", "/app/smaller.jar"]
EXPOSE 8080
