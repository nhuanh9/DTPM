package com.example.demo.service.impl;

import com.example.demo.repository.ServicesRepository;
import com.example.demo.model.Services;
import com.example.demo.service.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicesServiceImpl implements ServicesService {
    @Autowired
    private ServicesRepository serviceRepository;

    @Override
    public Iterable<Services> findAll() {
        return serviceRepository.findAll();
    }

    @Override
    public Services findById(Long id) {
        return serviceRepository.findById(id).get();
    }

    @Override
    public void save(Services service) {
        serviceRepository.save(service);
    }

    @Override
    public void remove(Long id) {
        serviceRepository.deleteById(id);
    }
}
