package com.packt.spring5.dto;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class Stock {

    @NotNull
    @Size(max = 10)
    private String id;

    @NotNull
    @Size(max = 100)
    private String name;

    @NotNull
    @DecimalMin("0.01")
    private Double price;

}
