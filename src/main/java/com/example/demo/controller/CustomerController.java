package com.example.demo.controller;

import java.util.Optional;

import com.example.demo.base.BaseConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Customer;
import com.example.demo.services.ICustomerService;

@RestController
@RequestMapping(BaseConst.BASE_API + "/customer")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        try {
            return ResponseEntity.ok(customerService.findAll());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<?> findById(@PathVariable("customerId") Integer customerId) {
        try {
            Optional<Customer> oCustomer = customerService.findById(customerId);
            if (oCustomer.isPresent())
                return ResponseEntity.ok(oCustomer.get());
            return new ResponseEntity<>("Customer is not exist", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> insert(@RequestBody Customer customer) {
        try {
            Optional<Customer> oCustomer = customerService.insert(customer);
            if (oCustomer.isPresent())
                return ResponseEntity.ok(oCustomer.get());
            return new ResponseEntity<>("Insert error", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Server error", HttpStatus.BAD_GATEWAY);
        }
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<?> update(@PathVariable("customerId") Integer customerId, @RequestBody Customer customer) {
        try {
            Optional<Customer> oCustomer = customerService.update(customerId, customer);
            if (oCustomer.isPresent())
                return ResponseEntity.ok(oCustomer.get());
            return new ResponseEntity<>("Update error", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Server error", HttpStatus.BAD_GATEWAY);
        }
    }
}
