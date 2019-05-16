package com.packt.spring5.rest.exception.handling;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<StockError> handleException(IllegalArgumentException exception) {
        return ResponseEntity.badRequest().body(new StockError("00102", exception.getMessage()));
    }
}
