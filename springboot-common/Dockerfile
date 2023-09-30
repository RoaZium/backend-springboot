FROM openjdk:17-alpine

WORKDIR $APP_HOME

COPY build/libs/springboot-common-0.0.1-SNAPSHOT.jar springboot-common.jar

EXPOSE 8080

CMD ["java", "-jar", "/springboot-common.jar"]