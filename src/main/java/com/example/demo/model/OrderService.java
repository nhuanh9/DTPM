package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class OrderService {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long times;

    private Date time;

    private Double totalMoney;

    @ManyToOne
    @JoinColumn(name = "id_service")
    private Services services;

    @ManyToOne
    @JoinColumn(name = "id_order")
    private Orders orders;
}
