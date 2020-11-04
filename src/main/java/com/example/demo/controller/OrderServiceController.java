package com.example.demo.controller;

import com.example.demo.model.OrderService;
import com.example.demo.service.impl.OrderServiceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@CrossOrigin("*")
@RestController
public class OrderServiceController {
    @Autowired
    private OrderServiceServiceImpl orderServiceService;

    @RequestMapping(value = "/api/orderservices", method = RequestMethod.GET)
    public ResponseEntity<Iterable<OrderService>> listAllOrderService() {
        Iterable<OrderService> orderServices = orderServiceService.findAll();
        if (orderServices == null) {
            return new ResponseEntity<Iterable<OrderService>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Iterable<OrderService>>(orderServices, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/orderservices/{id}", method = RequestMethod.GET)
    public ResponseEntity<OrderService> getOrderService(@PathVariable("id") Long id) {
        OrderService orderService = orderServiceService.findById(id);
        if (orderService == null) {
            return new ResponseEntity<OrderService>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<OrderService>(orderService, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/orderservices", method = RequestMethod.POST)
    public ResponseEntity<Void> addOrderService(@RequestBody OrderService orderService, UriComponentsBuilder ucBuilder) {
        orderServiceService.save(orderService);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/orderservices/{id}").buildAndExpand(orderService.getTimes()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/api/orderservices/{id}", method = RequestMethod.PUT)
    public ResponseEntity<OrderService> updateOrderService(@PathVariable("id") Long id, @RequestBody OrderService orderService) {
        OrderService orderService1 = orderServiceService.findById(id);

        if (orderService1 == null) {
            return new ResponseEntity<OrderService>(HttpStatus.NOT_FOUND);
        }
        orderService1.setOrders(orderService.getOrders());
        orderService1.setServices(orderService.getServices());

        orderServiceService.save(orderService1);
        return new ResponseEntity<OrderService>(HttpStatus.OK);
    }

    @RequestMapping(value = "/api/orderservices/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<OrderService> deleteOrderService(@PathVariable("id") Long id) {
        OrderService orderService = orderServiceService.findById(id);
        if (orderService == null) {
            return new ResponseEntity<OrderService>(HttpStatus.NOT_FOUND);
        }
        orderServiceService.remove(id);
        return new ResponseEntity<OrderService>(HttpStatus.OK);
    }
}
