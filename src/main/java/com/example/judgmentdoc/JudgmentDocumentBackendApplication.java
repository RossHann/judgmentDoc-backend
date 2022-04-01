package com.example.judgmentdoc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class JudgmentDocumentBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(JudgmentDocumentBackendApplication.class, args);
    }

}
