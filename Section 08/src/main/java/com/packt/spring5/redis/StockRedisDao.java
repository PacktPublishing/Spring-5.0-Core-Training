package com.packt.spring5.redis;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.packt.spring5.dao.StockDao;
import com.packt.spring5.dto.Stock;

@Repository
@Qualifier("redis")
public class StockRedisDao implements StockDao {

    @Resource
    private StockRedisRepository stockRedisRepository;

    @Resource
    private StockRedisMapper stockRedisMapper;

    @Override
    public Stock findById(String id) {
        return stockRedisRepository.findById(id)
                .map(stockEntity -> stockRedisMapper.toDto(stockEntity))
                .orElse(null);
    }

    @Override
    @Transactional
    public void save(Stock stock) {
        stockRedisRepository.save(stockRedisMapper.toEntity(stock));
    }
}
