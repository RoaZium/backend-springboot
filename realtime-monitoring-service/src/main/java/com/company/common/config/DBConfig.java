package com.company.common.config;

import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class DBConfig {

    public static void loadConfig() throws Exception {
        // Docker 환경 체크
        if (Files.exists(Paths.get("/.dockerenv")) || System.getenv("DOCKER_CONTAINER") != null) {
            System.out.println("Docker 환경 - application.yml 사용");
            return;
        }

        // exe 환경에서만 외부 설정 파일 로드
        if (!Files.exists(Paths.get("dbconfig.properties"))) {
            System.out.println("개발 환경 - application.yml 사용");
            return;
        }

        // 외부 설정 파일 로드
        Properties prop = new Properties();
        try (FileInputStream fis = new FileInputStream("dbconfig.properties")) {
            prop.load(fis);
        }

        String dbType = prop.getProperty("db.type", "mariadb").toLowerCase();

        if ("mariadb".equals(dbType)) {
            System.setProperty("spring.datasource.url", prop.getProperty("db.url.mariadb"));
            System.setProperty("spring.datasource.username", prop.getProperty("db.username.mariadb"));
            System.setProperty("spring.datasource.password", prop.getProperty("db.password.mariadb"));
            System.setProperty("spring.datasource.driver-class-name", prop.getProperty("db.driver.mariadb"));
        } else if ("mssql".equals(dbType)) {
            System.setProperty("spring.datasource.url", prop.getProperty("db.url.mssql"));
            System.setProperty("spring.datasource.username", prop.getProperty("db.username.mssql"));
            System.setProperty("spring.datasource.password", prop.getProperty("db.password.mssql"));
            System.setProperty("spring.datasource.driver-class-name", prop.getProperty("db.driver.mssql"));
        } else {
            throw new IllegalArgumentException("Unsupported db.type: " + dbType);
        }

        System.out.println("외부 설정 파일에서 " + dbType + " DB 설정 로드");
    }
}