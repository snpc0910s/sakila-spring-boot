package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Customer;

public interface ICustomerService {
    public List<Customer> findAll();

    public Optional<Customer> findById(Integer id);

    public boolean exist(Integer id);

    public Optional<Customer> insert(Customer saveCustomer);

    public Optional<Customer> update(Integer customerId, Customer saveCustomer);
}

