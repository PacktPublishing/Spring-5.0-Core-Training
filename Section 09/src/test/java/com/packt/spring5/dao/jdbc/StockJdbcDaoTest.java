package com.packt.spring5.dao.jdbc;

import static org.assertj.core.api.Assertions.assertThat;

import javax.annotation.Resource;

import com.packt.spring5.test.SpringTestSupport;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTestContextBootstrapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.jdbc.JdbcTestUtils;

import com.packt.spring5.dao.StockDao;
import com.packt.spring5.dto.Stock;

@SpringTestSupport
@BootstrapWith(SpringBootTestContextBootstrapper.class)
class StockJdbcDaoTest {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Resource
    private StockDao stockDao;

    @Test
    void shouldFindExistingStock() {
        assertThat(stockDao.findById("NASDAQ:GOOG")).isNotNull();
    }

    @Test
    void shouldCreateStock() throws Exception {
        int stocksNumBefore = retrieveStocksNum();
        Stock stock = new Stock();
        stock.setId("NASDAQ:FB");
        stock.setName("Facebook Inc.");
        stock.setPrice(135.5);
        stockDao.save(stock);
        int stocksNumAfter = retrieveStocksNum();
        assertThat(stocksNumAfter).isGreaterThan(stocksNumBefore);
    }

    @AfterTransaction
    void shouldCountInitialNumberOfStocks() {
        int stocksNum = retrieveStocksNum();
        assertThat(stocksNum).isEqualTo(3);
        System.out.println(String.format("Number of rows verified %s", stocksNum));
    }

    private int retrieveStocksNum() {
        return JdbcTestUtils.countRowsInTable(jdbcTemplate, "stocks");
    }
}