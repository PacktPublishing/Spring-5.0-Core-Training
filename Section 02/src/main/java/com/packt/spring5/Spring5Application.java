package com.packt.spring5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Spring5Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Spring5Application.class, args);
		System.out.println(context.getBean(PlainSimpleLogic.class));
		System.out.println(context.getBean(PlainSimpleLogic.class));
		System.out.println(context.getBean(PlainSimpleLogic.class));

		context.getBean(PlainSimpleLogic.class).doSomeWork();
	}
}
