package com.example.demo.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.demo.repo.CountryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Country;
import com.example.demo.services.ICountryService;

@Service
public class CountryServiceImpl implements ICountryService {

    @Autowired
    private CountryRepo countryRepo;

    @Transactional(readOnly = true)
    @Override
    public List<Country> findAll() {
        return countryRepo.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Country> findById(Integer id) {
        return countryRepo.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean exist(Integer id) {
        return countryRepo.existsById(id);
    }

    @Transactional(readOnly = false)
    @Override
    public Optional<Country> insert(Country saveCountry) {
        Country country = new Country();
        country.setCountryId(null);
        country.setCountry(saveCountry.getCountry());
        country.setLastUpdate(saveCountry.getLastUpdate());
        Country o = countryRepo.save(country);
        return countryRepo.findById(o.getCountryId());
    }

    @Transactional(readOnly = false)
    @Override
    public Optional<Country> update(Integer countryId, Country saveCountry) {
        saveCountry.setCountryId(countryId);
        Optional<Country> countryOptional = countryRepo.findById(saveCountry.getCountryId());
        if (countryOptional.isPresent()) {
            Country country = countryOptional.get();
            country.setCountry(saveCountry.getCountry());
            country.setLastUpdate(saveCountry.getLastUpdate());
            Country o = countryRepo.save(country);
            return countryRepo.findById(o.getCountryId());
        }
        return Optional.empty();
    }
//
//    @Transactional(readOnly = true)
//    @Override
//    public List<Country> getDynamicWhereCondition(Country country, Pageable page) {
//        return countryRepo.getDynamicWhereCondition(country,page);
//    }
}

