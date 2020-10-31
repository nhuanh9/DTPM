package com.example.demo.repository;

import com.example.demo.model.HouseDay;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Date;

public interface HouseDayRepository extends JpaRepository<HouseDay, Date> {
}
