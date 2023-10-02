FROM openjdk:11-jdk-slim

WORKDIR /app

COPY build/libs/my-spring-boot-app-0.0.1-SNAPSHOT.jar my-spring-boot-app.jar

CMD ["java", "-jar", "my-spring-boot-app.jar"]

- `FROM`: 기본 이미지를 지정합니다. 이 예에서는 Java 11 이미지를 사용합니다.
- `WORKDIR`: 이미지의 작업 디렉토리를 지정합니다. 이 예에서는 `/app` 디렉토리를 사용합니다.
- `COPY`: 로컬 파일을 이미지로 복사합니다. 이 예에서는 `build/libs/my-spring-boot-app-0.0.1-SNAPSHOT.jar` 파일을 복사합니다.
- `CMD`: 이미지가 실행될 때 실행할 명령을 지정합니다. 이 예에서는 `java -jar my-spring-boot-app.jar` 명령을 사용합니다.

Dockerfile을 생성했으면 다음 명령어를 사용하여 Docker 이미지를 빌드합니다.

```
docker build -t my-spring-boot-app:0.0.1-SNAPSHOT .
```

이 명령어는 `my-spring-boot-app`이라는 이름의 Docker 이미지를 `0.0.1-SNAPSHOT` 버전으로 빌드합니다.

1. **Docker 이미지 푸시**

Docker Hub에 Docker 이미지를 푸시하려면 다음 명령어를 사용합니다.

```
docker push my-spring-boot-app:0.0.1-SNAPSHOT
```

이 명령어는 `my-spring-boot-app`이라는 이름의 Docker 이미지를 `0.0.1-SNAPSHOT` 버전으로 Docker Hub에 푸시합니다.

2. **Docker 이미지 실행**

Docker 이미지를 실행하려면 다음 명령어를 사용합니다.

```
docker run -p 8080:8080 my-spring-boot-app:0.0.1-SNAPSHOT
```

이 명령어는 `my-spring-boot-app`이라는 이름의 Docker 이미지를 실행하고 포트 `8080`을 `8080`으로 노출합니다.

이제 Spring Boot 프로젝트가 Docker 이미지로 배포되었습니다. 이 이미지를 사용하여 Docker Hub에서 배포하거나, EC2 인스턴스 또는 다른 Docker 호스트에 로컬로 배포할 수 있습니다.