package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import com.example.demo.entity.Country;


public interface ICountryService {
    public List<Country> findAll();

    public Optional<Country> findById(Integer id);

    public boolean exist(Integer id);

    public Optional<Country> insert(Country saveCountry);

    public Optional<Country> update(Integer countryId, Country saveCountry);
//
//    public List<Country> getDynamicWhereCondition(Country country, Pageable page);
}

