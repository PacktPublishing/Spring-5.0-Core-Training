package com.packt.spring5.dao;

import com.packt.spring5.dto.Stock;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StockDao {

    Mono<Stock> findById(String id);

    void save(Stock stock);

    Flux<Stock> findAllByIds(String[] split);
}
