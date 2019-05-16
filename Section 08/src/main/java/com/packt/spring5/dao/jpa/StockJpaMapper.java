package com.packt.spring5.dao.jpa;

import org.mapstruct.Mapper;

@Mapper
public interface StockJpaMapper {
    Stock toEntity(com.packt.spring5.dto.Stock stock);

    com.packt.spring5.dto.Stock toDto(Stock stock);
}
