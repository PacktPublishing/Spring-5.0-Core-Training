package com.packt.spring5.redis;

import org.mapstruct.Mapper;

@Mapper
public interface StockRedisMapper {
    Stock toEntity(com.packt.spring5.dto.Stock stock);

    com.packt.spring5.dto.Stock toDto(Stock stock);
}
