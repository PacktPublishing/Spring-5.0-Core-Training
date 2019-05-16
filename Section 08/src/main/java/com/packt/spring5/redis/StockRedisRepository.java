package com.packt.spring5.redis;

import org.springframework.data.repository.CrudRepository;

public interface StockRedisRepository extends CrudRepository<Stock, String> {

}
