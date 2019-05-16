package com.packt.spring5.controller;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.NestedServletException;

import com.packt.spring5.dao.StockDao;
import com.packt.spring5.dto.Stock;

@ExtendWith(SpringExtension.class)
class StockDataAwareControllerStandaloneTest {

    private MockMvc mockMvc;

    private StockDao stockDao;

    @BeforeEach
    void setup() {
        stockDao = Mockito.mock(StockDao.class);

        StockDataAwareController stockDataAwareController = new StockDataAwareController(stockDao);
        this.mockMvc = MockMvcBuilders.standaloneSetup(stockDataAwareController).build();
    }

    @Test
    void shouldGetStockById() throws Exception {
        String stockId = "NASDAQ:GOOG";
        Stock stock = new Stock();
        stock.setId(stockId);
        stock.setName("Alphabet Inc.");
        stock.setPrice(975.63);
        when(stockDao.findById(stockId)).thenReturn(stock);
        mockMvc.perform(get("/stock/{id}", stockId)).andExpect(status().isOk());
    }

    @Test
    void shouldHandleAbsentGetStockById() throws Exception {
        Throwable exception = assertThrows(NestedServletException.class, () -> {
            mockMvc.perform(get("/stock/{id}", "NASDAQ:GOOG")).andExpect(status().isOk());
        });
        assertTrue(exception.getMessage().contains("Sorry we do not have stock information for given company"));
    }
}