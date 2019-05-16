package com.packt.spring5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.ConfigurableApplicationContext;

import com.packt.spring5.aop.TracibleService;

@SpringBootApplication
public class GettingStartedSection3Application {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(GettingStartedSection3Application.class);
		ConfigurableApplicationContext ctx = app.run(args);

		ctx.getBean(TracibleService.class).hello("aop");
	}
}
