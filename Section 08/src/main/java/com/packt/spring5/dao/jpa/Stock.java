package com.packt.spring5.dao.jpa;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "stocks")
public class Stock implements Serializable {

    @Id
    private String id;
    private String name;
    private Double price;

}
