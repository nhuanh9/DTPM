package com.example.demo.controller;

import com.example.demo.model.CategoryHouse;
import com.example.demo.model.HouseDay;
import com.example.demo.service.impl.CategoryHouseServiceImpl;
import com.example.demo.service.impl.HouseDayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Date;

@CrossOrigin("*")
@RestController
public class HouseDayController {
    @Autowired
    private HouseDayServiceImpl houseDayService;

    @RequestMapping(value = "/api/houseDays", method = RequestMethod.GET)
    public ResponseEntity<Iterable<HouseDay>> listAllHouseDay() {
        Iterable<HouseDay> houseDays = houseDayService.findAll();
        if (houseDays == null) {
            return new ResponseEntity<Iterable<HouseDay>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Iterable<HouseDay>>(houseDays, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/houseDays/{id}", method = RequestMethod.GET)
    public ResponseEntity<HouseDay> getHouseDay(@PathVariable("id") Date id) {
        HouseDay houseDay = houseDayService.findById(id);
        if (houseDay == null) {
            return new ResponseEntity<HouseDay>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<HouseDay>(houseDay, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/houseDays", method = RequestMethod.POST)
    public ResponseEntity<Void> createHouseDay(@RequestBody HouseDay houseDay) {
        houseDayService.save(houseDay);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/api/houseDays/{id}", method = RequestMethod.PUT)
    public ResponseEntity<HouseDay> updateHouseDay(@PathVariable("id") Date id, @RequestBody HouseDay houseDay) {
        HouseDay houseDay1 = houseDayService.findById(id);

        if (houseDay1 == null) {
            return new ResponseEntity<HouseDay>(HttpStatus.NOT_FOUND);
        }
        houseDay1.setHouse(houseDay.getHouse());
        houseDay1.setStatus(houseDay.getStatus());

        houseDayService.save(houseDay1);
        return new ResponseEntity<HouseDay>(HttpStatus.OK);
    }

    @RequestMapping(value = "/api/houseDays/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<HouseDay> deleteHouseDay(@PathVariable("id") Date id) {
        HouseDay houseDay = houseDayService.findById(id);
        if (houseDay == null) {
            return new ResponseEntity<HouseDay>(HttpStatus.NOT_FOUND);
        }
        houseDayService.remove(id);
        return new ResponseEntity<HouseDay>(HttpStatus.OK);
    }


}
