package com.packt.spring5.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.packt.spring5.dao.StockDao;
import com.packt.spring5.dto.Stock;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
public class StockDataAwareController {

    @Resource
    @Qualifier("redis")
    private StockDao stockDao;

    @GetMapping("/stock/{id}")
    public Mono<Stock> getInfo(@PathVariable("id") String id) {
        Mono<Stock> stock = stockDao.findById(id);
        if (stock == null) {
            throw new IllegalStateException("Sorry we do not have stock information for given company");
        }
        return stock;
    }

    @PutMapping("/stock/{id}")
    public void saveStock(@PathVariable("id") String id, @RequestBody Stock stock) {
        stockDao.save(stock);
    }

    @GetMapping("/stocks/{ids}")
    public Flux<Stock> findAllByIds(@PathVariable("ids") String ids){
        return stockDao.findAllByIds(ids.split(","));
    }

}
