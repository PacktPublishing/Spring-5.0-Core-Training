package com.packt.spring5.dao.jpa;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JpaConfiguration {

    @Bean
    public StockJpaMapper stockJpaMapper() {
        return Mappers.getMapper(StockJpaMapper.class);
    }
}
