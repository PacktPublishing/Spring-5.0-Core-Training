package com.packt.spring5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GettingStartedForSection10Application {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(GettingStartedForSection10Application.class);
        app.run(args);
    }
}
