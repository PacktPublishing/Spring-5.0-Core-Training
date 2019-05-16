package com.packt.spring5.dao;

import com.packt.spring5.dto.Stock;

public interface StockDao {

    Stock findById(String id);

    void save(Stock stock);
}
