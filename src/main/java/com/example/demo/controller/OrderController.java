package com.example.demo.controller;

import com.example.demo.model.Orders;
import com.example.demo.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@CrossOrigin("*")
@RestController
public class OrderController {
    @Autowired
    private OrderServiceImpl orderService;

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
        orderService.save(order);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/orders/{id}").buildAndExpand(order.getIdOrder()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/api/orders/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Orders> updateOrder(@PathVariable("id") Long id, @RequestBody Orders order) {
        Orders order1 = orderService.findById(id);

        if (order1 == null) {
            return new ResponseEntity<Orders>(HttpStatus.NOT_FOUND);
        }
        order1.setPersonOrderName(order.getPersonOrderName());
        order1.setTelephoneNumber(order.getTelephoneNumber());
        order1.setBookingDate(order.getBookingDate());
        order1.setArrivalDate(order.getArrivalDate());
        order1.setDateGo(order.getDateGo());
        order1.setStatus(order.getStatus());
        order1.setAccount(order.getAccount());
        order1.setEvaluate(order.getEvaluate());
        order1.setTotal(order.getTotal());
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
