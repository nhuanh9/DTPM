package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idAccount;

    private String userName;

    private String password;

    private String fullName;

    private String telephoneNumber;

    private String avt;

    @ManyToOne
    @JoinColumn(name = "id_role")
    private Role role;
}
