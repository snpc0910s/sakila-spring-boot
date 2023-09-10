package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Rental;

public interface IRentalService {
    public List<Rental> findAll();

    public Optional<Rental> findById(Integer id);

    public boolean exist(Integer id);

    public Optional<Rental> insert(Rental saveRental);

    public Optional<Rental> update(Integer rentalId, Rental saveRental);
}

