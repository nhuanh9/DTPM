package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idOrder;

    private Date bookingDate;

    private Date startDate;

    private Date endDate;
    @ManyToOne
    @JoinColumn(name = "id_house")
    private House house;
}
