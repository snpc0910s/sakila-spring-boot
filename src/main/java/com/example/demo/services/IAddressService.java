package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Address;

public interface IAddressService {
    public List<Address> findAll();

    public Optional<Address> findById(Integer id);

    public boolean exist(Integer id);

    public Optional<Address> insert(Address saveAddress);

    public Optional<Address> update(Integer addressId, Address saveAddress);
}

