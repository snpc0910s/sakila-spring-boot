package com.example.demo.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.City;
import com.example.demo.entity.Country;
import com.example.demo.exception.ObjectNotFoundException;
import com.example.demo.repo.CityRepo;
import com.example.demo.repo.CountryRepository;
import com.example.demo.services.ICityService;

@Service
public class CityServiceImpl implements ICityService {

    @Autowired
    private CityRepo cityRepo;
    
    @Autowired
    private CountryRepository countryRepo;


    @Transactional(readOnly = true)
    @Override
    public List<City> findAll() {
        return cityRepo.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<City> findById(Integer id) {
        return cityRepo.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean exist(Integer id) {
        return cityRepo.existsById(id);
    }

    @Transactional(readOnly = false)
    @Override
    public Optional<City> insert(City saveCity) {
        Optional<Country> oCountry = countryRepo.findById(saveCity.getCountry().getCountryId());
        if(oCountry.isPresent()) {
            City city = new City();
            city.setCityId(null);
            city.setCity(saveCity.getCity());
            city.setCountry(oCountry.get());
            city.setLastUpdate(saveCity.getLastUpdate());
            City o = cityRepo.save(city);
            return cityRepo.findById(o.getCityId());
        }
        throw new ObjectNotFoundException("Country is not exist.");
    }

    @Transactional(readOnly = false)
    @Override
    public Optional<City> update(Integer cityId, City saveCity) {
        saveCity.setCityId(cityId);
        Optional<City> cityOptional = cityRepo.findById(saveCity.getCityId());
        Optional<Country> oCountry = countryRepo.findById(saveCity.getCountry().getCountryId());
        if (cityOptional.isPresent() && oCountry.isPresent()) {
            City city = cityOptional.get();
            city.setCity(saveCity.getCity());
            city.setCountry(oCountry.get());
            city.setLastUpdate(saveCity.getLastUpdate());
            City o = cityRepo.save(city);
            return cityRepo.findById(o.getCityId());
        }
        throw new ObjectNotFoundException("Country is not exist.");
    }
}

