package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.City;

public interface ICityService {
    public List<City> findAll();

    public Optional<City> findById(Integer id);

    public boolean exist(Integer id);

    public Optional<City> insert(City saveCity);

    public Optional<City> update(Integer cityId, City saveCity);
}

