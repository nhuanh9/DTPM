package com.example.demo.service;

import com.example.demo.model.OrderService;

public interface OrderServiceService {
    Iterable<OrderService> findAll();

    OrderService findById(Long id);

    void save(OrderService orderService);

    void remove(Long id);
}
