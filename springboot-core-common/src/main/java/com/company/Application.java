package com.company;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import java.io.IOException;
import java.net.ServerSocket;

@EnableAutoConfiguration
@SpringBootApplication
@RestController
public class Application {

    private static ServerSocket serverSocket;

    public static void main(String[] args) {
        if (isPortInUse(38080)) {
            System.out.println("Application is already running.");
            System.exit(1);
        } else {
            SpringApplication.run(Application.class, args);
            System.out.println("Application started successfully.");
        }
    }

    private static boolean isPortInUse(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            return false;
        } catch (IOException e) {
            return true;
        }
    }
}
