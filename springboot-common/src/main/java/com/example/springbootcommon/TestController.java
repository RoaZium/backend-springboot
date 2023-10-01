package com.example.springbootcommon;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/")
    public String testApi(){
        return "Hello API Hwang JinWook~";
    }
}