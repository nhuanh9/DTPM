package com.example.demo.service;

import com.example.demo.model.Orders;

public interface OrderService {
    Iterable<Orders> findAll();

    Orders findById(Long id);

    void save(Orders order);

    void remove(Long id);
}
