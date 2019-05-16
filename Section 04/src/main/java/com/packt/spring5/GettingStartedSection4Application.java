package com.packt.spring5;

import com.packt.spring5.tx.BusinessService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class GettingStartedSection4Application {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(GettingStartedSection4Application.class);
        ConfigurableApplicationContext ctx = app.run(args);

        ctx.getBean("businessServiceProgrammaticTx", BusinessService.class).doBusiness();

        ctx.getBean("businessServiceDeclarativeTx", BusinessService.class).doBusiness();
    }
}
