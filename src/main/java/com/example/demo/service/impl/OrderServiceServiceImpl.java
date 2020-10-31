package com.example.demo.service.impl;

import com.example.demo.model.OrderService;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.OrderServiceRepository;

import com.example.demo.service.OrderServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceServiceImpl implements OrderServiceService {
    @Autowired
    private OrderServiceRepository orderServiceRepository;

    @Override
    public Iterable<OrderService> findAll() {
        return orderServiceRepository.findAll();
    }

    @Override
    public OrderService findById(Long id) {
        return orderServiceRepository.findById(id).get();
    }

    @Override
    public void save(OrderService orderService) {
        orderServiceRepository.save(orderService);
    }

    @Override
    public void remove(Long id) {
        orderServiceRepository.deleteById(id);
    }
}
