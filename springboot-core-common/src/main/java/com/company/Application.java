package com.company;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import java.util.Scanner;

@EnableAutoConfiguration
@SpringBootApplication
@RestController
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

        // 사용자 입력을 기다리는 스레드 추가
        Thread inputThread = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Press Enter to exit...");
            scanner.nextLine();
            System.exit(0);
        });

        inputThread.setDaemon(true); // 메인 스레드 종료 시 함께 종료되도록 설정
        inputThread.start();
    }
}
