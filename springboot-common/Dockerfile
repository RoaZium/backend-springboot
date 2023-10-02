FROM openjdk:17-alpine

RUN echo "127.0.0.1 host.docker.internal"

EXPOSE 8080

COPY build/libs/springboot-common-0.0.1.jar springboot-common.jar

CMD ["java", "-jar", "/springboot-common.jar"]