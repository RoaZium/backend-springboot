package com.company;

import com.company.common.config.DBConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import java.util.Scanner;

@EnableAutoConfiguration
@SpringBootApplication
@RestController
public class Main {
    public static void main(String[] args) {
        try {
            DBConfig.loadConfig(); // DB 설정 로드 호출
        } catch (Exception e) {
            System.err.println("Failed to load DB configuration: " + e.getMessage());
        }
        SpringApplication.run(Main.class, args);
    }
}
