package com.packt.spring5.dao.jdbc;

import javax.annotation.Resource;

import com.packt.spring5.dao.StockDao;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.packt.spring5.dto.Stock;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Qualifier("jdbc")
public class StockJdbcDao implements StockDao {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public Stock findById(String id) {
        String sqlQuery = "SELECT id, name, price FROM stocks WHERE id = ?";
        return jdbcTemplate.queryForObject(sqlQuery, new Object[] { id }, new BeanPropertyRowMapper<>(Stock.class));
    }

    @Override
    @Transactional
    public void save(Stock stock) {
        String sqlQuery = "MERGE INTO stocks KEY(id) VALUES (?, ?, ?)";
        jdbcTemplate.update(sqlQuery, stock.getId(), stock.getName(), stock.getPrice());
    }
}
