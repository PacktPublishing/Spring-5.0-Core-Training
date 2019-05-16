package com.packt.spring5.rest.exception.handling;

import lombok.Value;

@Value
public class StockError {
    String code;
    String message;
}
