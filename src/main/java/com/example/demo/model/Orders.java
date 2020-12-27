package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idOrder;

    private Date bookingDate;

    private Date startDate;

    private Date endDate;

    private String personName;

    private String account;

    private String telephoneNumber;
    @ManyToOne
    @JoinColumn(name = "id_house")
    private House house;

    @ManyToMany
    Set<Services> services;

    private Double servicePrice;
    private Double housePrice;
    private Double totalPrice;
}
