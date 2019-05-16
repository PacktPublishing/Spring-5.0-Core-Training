package com.packt.spring5.redis;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.packt.spring5.dao.StockDao;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@Qualifier("redis")
public class StockRedisDao implements StockDao {

    @Resource
    private StockRedisMapper stockRedisMapper;

    @Resource
    private ReactiveRedisTemplate<String, Stock> reactiveRedisTemplate;

    @Override
    public Mono<com.packt.spring5.dto.Stock> findById(String id) {
        return reactiveRedisTemplate
                .opsForValue()
                .get(id)
                .map(stock -> stockRedisMapper.toDto(stock));
    }

    @Override
    @Transactional
    public void save(com.packt.spring5.dto.Stock stock) {
        reactiveRedisTemplate
                .opsForValue()
                .set(stock.getId(), stockRedisMapper.toEntity(stock))
                .subscribe();
    }

    @Override
    public Flux<com.packt.spring5.dto.Stock> findAllByIds(String[] ids) {
        return Flux.fromArray(ids)
                .map(id -> reactiveRedisTemplate.opsForValue().get(id))
                .flatMap(Flux::from)
                .map(stock -> stockRedisMapper.toDto(stock));
    }

}
