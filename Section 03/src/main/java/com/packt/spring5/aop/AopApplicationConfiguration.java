package com.packt.spring5.aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:aop-context.xml")
public class AopApplicationConfiguration {

//    @Bean
    public TracibleService tracibleService() {
        return new TracibleService();
    }
}
