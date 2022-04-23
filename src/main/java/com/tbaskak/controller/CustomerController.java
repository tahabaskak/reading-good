package com.tbaskak.controller;

import com.tbaskak.entity.Customer;
import com.tbaskak.model.customer.CustomerEmailDto;
import com.tbaskak.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;
    
    @PostMapping("/customerSave")
    public ResponseEntity Save(@Valid @RequestBody Customer customerRequest) {
        return new ResponseEntity<>(customerService.save(customerRequest), HttpStatus.CREATED);
    }

    @PutMapping("/customerUpdate")
    public ResponseEntity Update(@Valid @RequestBody Customer customerUpdate){
        return new ResponseEntity<>(customerService.update(customerUpdate),HttpStatus.OK);
    }

    @GetMapping("/allCustomers")
    public ResponseEntity getCustomers(){
        return new ResponseEntity<>(customerService.allCustomer(),HttpStatus.OK);
    }

    @PostMapping("getCustomerByEmail")
    public ResponseEntity getCustomerByEmail(@Valid @RequestBody CustomerEmailDto customerEmailDto){
        return new ResponseEntity<>(customerService.getCustomerByEmail(customerEmailDto),HttpStatus.OK);
    }

}
