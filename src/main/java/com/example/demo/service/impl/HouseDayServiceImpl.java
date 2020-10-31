package com.example.demo.service.impl;

import com.example.demo.model.HouseDay;
import com.example.demo.repository.HouseDayRepository;
import com.example.demo.service.HouseDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class HouseDayServiceImpl implements HouseDayService {
    @Autowired
    private HouseDayRepository houseDayRepository;

    @Override
    public Iterable<HouseDay> findAll() {
        return houseDayRepository.findAll();
    }

    @Override
    public HouseDay findById(Date date) {
        return houseDayRepository.findById(date).get();
    }

    @Override
    public void save(HouseDay houseDay) {
        houseDayRepository.save(houseDay);
    }

    @Override
    public void remove(Date date) {
        houseDayRepository.deleteById(date);
    }
}
