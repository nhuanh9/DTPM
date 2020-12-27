package com.example.demo.controller;

import com.example.demo.model.HouseDay;
import com.example.demo.model.Orders;
import com.example.demo.model.Services;
import com.example.demo.service.HouseDayService;
import com.example.demo.service.impl.OrderServiceImpl;
import com.example.demo.service.impl.ServicesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Date;

@CrossOrigin("*")
@RestController
public class OrderController {
    @Autowired
    private OrderServiceImpl orderService;
    @Autowired
    private ServicesServiceImpl servicesService;
    @Autowired
    private HouseDayService houseDayService;
    private long oneDay = 86400000;

    @RequestMapping(value = "/api/orders", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Orders>> listAllOrder() {
        Iterable<Orders> order = orderService.findAll();
        if (order == null) {
            return new ResponseEntity<Iterable<Orders>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Iterable<Orders>>(order, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/orders/{id}", method = RequestMethod.GET)
    public ResponseEntity<Orders> getOrder(@PathVariable("id") Long id) {
        Orders order = orderService.findById(id);
        if (order == null) {
            return new ResponseEntity<Orders>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Orders>(order, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/orders", method = RequestMethod.POST)
    public ResponseEntity<Void> addOrder(@RequestBody Orders order, UriComponentsBuilder ucBuilder) {
        order.setHousePrice(getHousePrice(order));
        order.setServicePrice(getServicePrice(order));
        order.setTotalPrice(getTotalPrice(order));
        orderService.save(order);
        long startDate = order.getStartDate().getTime();
        long endDate = order.getEndDate().getTime();
        for (long i = startDate; i <= endDate; i += oneDay) {
            Date date1 = new Date(i);
            houseDayService.save(new HouseDay(date1, "1", order.getHouse()));
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/orders/{id}").buildAndExpand(order.getIdOrder()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    private Double getServicePrice(Orders orders) {
        Double price = Double.valueOf(0);
        for (Services service :
                orders.getServices()) {
            service = servicesService.findById(service.getIdService());
            price += service.getPrice();
        }
        return price;
    }

    private Double getHousePrice(Orders orders) {
        Double price = (orders.getEndDate().getTime() - orders.getStartDate().getTime()) / oneDay * orders.getHouse().getPrice();
        return price;
    }

    private Double getTotalPrice(Orders orders) {
        return getHousePrice(orders) + getServicePrice(orders);
    }

    @RequestMapping(value = "/api/orders/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Orders> updateOrder(@PathVariable("id") Long id, @RequestBody Orders order) {
        Orders order1 = orderService.findById(id);

        if (order1 == null) {
            return new ResponseEntity<Orders>(HttpStatus.NOT_FOUND);
        }
        order1.setBookingDate(order.getBookingDate());
        order1.setHouse(order.getHouse());
        order1.setAccount(order.getAccount());
        order1.setPersonName(order.getPersonName());
        order1.setTelephoneNumber(order.getTelephoneNumber());
        order1.setStartDate(order.getStartDate());
        order1.setEndDate(order.getEndDate());
        orderService.save(order1);
        return new ResponseEntity<Orders>(HttpStatus.OK);
    }

    @RequestMapping(value = "/api/orders/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Orders> deleteOrder(@PathVariable("id") Long id) {
        Orders order = orderService.findById(id);
        if (order == null) {
            return new ResponseEntity<Orders>(HttpStatus.NOT_FOUND);
        }
        orderService.remove(id);
        return new ResponseEntity<Orders>(HttpStatus.OK);
    }
}
