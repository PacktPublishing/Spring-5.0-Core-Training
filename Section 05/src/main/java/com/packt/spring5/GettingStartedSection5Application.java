package com.packt.spring5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GettingStartedSection5Application {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(GettingStartedSection5Application.class);
        app.run(args);
    }
}
