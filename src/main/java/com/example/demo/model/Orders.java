package com.example.demo.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idOrder;

    private String personOrderName;

    private String telephoneNumber;

    private Date bookingDate;

    private Date arrivalDate;

    private Date dateGo;

    private Double total;

    private Boolean status;

    private String evaluate;

    private String account;
}
