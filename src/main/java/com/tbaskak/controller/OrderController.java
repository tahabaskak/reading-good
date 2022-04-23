package com.tbaskak.controller;

import com.tbaskak.model.order.FilterOrderDto;
import com.tbaskak.model.order.OrderDto;
import com.tbaskak.service.BookService;
import com.tbaskak.service.CustomerService;
import com.tbaskak.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class OrderController {

    @Autowired
    CustomerService customerService;

    @Autowired
    BookService bookService;

    @Autowired
    OrderService orderService;

    @PostMapping("/orders")
    public ResponseEntity saveOrder(@Valid @RequestBody OrderDto model){
        return new ResponseEntity<>(orderService.save(model), HttpStatus.CREATED);
    }

    @PutMapping("/orders/{id}")
    public ResponseEntity cancel(@PathVariable String id){
        return new ResponseEntity<>(orderService.cancel(id),HttpStatus.OK);
    }

    @DeleteMapping("/orders/{id}")
    public ResponseEntity deleteOrder(@PathVariable String id){
        return new ResponseEntity<>(orderService.delete(id),HttpStatus.OK);
    }

    @GetMapping("/orders")
    public ResponseEntity getAllOrders(){
        return new ResponseEntity<>(orderService.getAllOrders(),HttpStatus.OK);
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity getOrderById(@PathVariable String id){
        return new ResponseEntity<>(orderService.getOrderById(id),HttpStatus.OK);
    }

    @PostMapping("/ordersWithDates")
    public ResponseEntity getOrdersWithDate(@Valid @RequestBody FilterOrderDto model){
        return new ResponseEntity<>(orderService.getOrdersWithDates(model.getStartDate(), model.getEndDate()),HttpStatus.OK);
    }
}
