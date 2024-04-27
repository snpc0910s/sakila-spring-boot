package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Country;
import com.example.demo.repo.custom.impl.dto.CountryCityAddressDto;

public interface ICountryService {
    public List<Country> findAll();

    public Optional<Country> findById(Integer id);

    public boolean exist(Integer id);

    public Optional<Country> insert(Country saveCountry);

    public Optional<Country> update(Integer countryId, Country saveCountry);

    public List<Country> getAllCustomByRangeId();

    public List<CountryCityAddressDto> getListByCityNameDSQL(String countryName);
}

