package com.packt.spring5.rest.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.packt.spring5.rest.exception.handling.StockError;

import lombok.AllArgsConstructor;
import lombok.Value;

@RestController
@RequestMapping(value = "/rest", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
public class StockRestController {

    @Value
    @AllArgsConstructor
    static class Stock {

        @NotNull
        @Size(max = 15)
        private String company;

        @NotNull
        @DecimalMin("0.01")
        private Double price;
    }

    private static final Map<String, Stock> STOCK_CATALOG = new HashMap<String, Stock>() {
        {
            put("NASDAQ:AAPL", new Stock("NASDAQ:AAPL", 155.45));
            put("NASDAQ:GOOG", new Stock("NASDAQ:GOOG", 975.63));
            put("NASDAQ:AMZN", new Stock("NASDAQ:AMZN", 1006.73));
        }
    };

    @GetMapping("/stock/{company}")
    public Stock getInfo(@PathVariable("company") String company) {

        validateRequest(company);

        Stock stock;
        if (STOCK_CATALOG.containsKey(company)) {
            stock = STOCK_CATALOG.get(company);
        } else {
            throw new IllegalStateException("Sorry we do not have stock information for given company");
        }
        return stock;
    }

    @PutMapping("/stock/{company}")
    public void storeInfo(@PathVariable("company") String company, @Valid @RequestBody Stock stock) {
        STOCK_CATALOG.put(company, stock);
    }

    @PostMapping("/stock/{company}")
    public void updateInfo(@PathVariable("company") String company, @Validated @RequestBody Stock stock) {
        STOCK_CATALOG.put(company, stock);
    }

    @ExceptionHandler(IllegalStateException.class)
    public StockError handleException(IllegalStateException exception) {
        return new StockError("00101", exception.getMessage());
    }

    private void validateRequest(String company) {
        if (company.matches("\\d+")) {
            throw new IllegalArgumentException("Company stock cannot only contain digits.");
        }
    }

}
