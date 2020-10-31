package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class HouseDay {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "id_house")
    private House house;

    @ManyToOne
    @JoinColumn(name = "id_order")
    private Orders orders;
}
