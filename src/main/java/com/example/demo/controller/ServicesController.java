package com.example.demo.controller;

import com.example.demo.model.Services;
import com.example.demo.service.ServicesService;
import com.example.demo.service.impl.ServicesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@CrossOrigin("*")
@RestController
public class ServicesController {
    @Autowired
    private ServicesServiceImpl servicesService;

    @RequestMapping(value = "/api/services", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Services>> listAllService() {
        Iterable<Services> services = servicesService.findAll();
        if (services == null) {
            return new ResponseEntity<Iterable<Services>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Iterable<Services>>(services, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/services/{id}", method = RequestMethod.GET)
    public ResponseEntity<Services> getUtilitie(@PathVariable("id") Long id) {
        Services services = servicesService.findById(id);
        if (services == null) {
            return new ResponseEntity<Services>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Services>(services, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/services", method = RequestMethod.POST)
    public ResponseEntity<Void> addUtilitie(@RequestBody Services services, UriComponentsBuilder ucBuilder) {
        servicesService.save(services);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/services/{id}").buildAndExpand(services.getIdService()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/api/services/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Services> updateUtilitie(@PathVariable("id") Long id, @RequestBody Services services) {
        Services services1 = servicesService.findById(id);

        if (services1 == null) {
            return new ResponseEntity<Services>(HttpStatus.NOT_FOUND);
        }
        services1.setName(services.getName());
        services1.setPrice(services.getPrice());
        services1.setDescription(services.getDescription());

        servicesService.save(services1);
        return new ResponseEntity<Services>(HttpStatus.OK);
    }

    @RequestMapping(value = "/api/services/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Services> deleteServices(@PathVariable("id") Long id) {
        Services services = servicesService.findById(id);
        if (services == null) {
            return new ResponseEntity<Services>(HttpStatus.NOT_FOUND);
        }
        servicesService.remove(id);
        return new ResponseEntity<Services>(HttpStatus.OK);
    }
}
