package com.packt.spring5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GettingStartedForSection6Application {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(GettingStartedForSection6Application.class);
        app.run(args);
    }
}
