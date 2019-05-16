package com.packt.spring5.redis;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

import redis.embedded.RedisServer;

@Configuration
@EnableRedisRepositories
public class RedisConfiguration {

    private RedisServer redisServer;

    @Bean
    public StockRedisMapper stockRedisMapper() {
        return Mappers.getMapper(StockRedisMapper.class);
    }

    @PostConstruct
    public void startEmbeddedRedisServer() throws IOException {
        redisServer = RedisServer.builder()
                .port(6379)
                .setting("bind 127.0.0.1")
                .setting("daemonize no")
                .setting("appendonly no")
                .setting("maxmemory 128M")
                .build();
        redisServer.start();
    }

    @PreDestroy
    public void stopEmbeddedRedisServer() throws IOException {
        redisServer.stop();
    }

    @Bean
    public ReactiveRedisConnectionFactory reactiveRedisConnectionFactory() {
        return new LettuceConnectionFactory();
    }

}
