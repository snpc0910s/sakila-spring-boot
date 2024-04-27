package com.example.demo.repo.custom;

import java.util.List;

import com.example.demo.entity.Country;
import com.example.demo.repo.custom.impl.dto.CountryCityAddressDto;

public interface ICountryRepoCustom {
    public List<Country> getListCustomByRangeId();
    public List<CountryCityAddressDto> getListByCityNameDSQL(String countryName);
}
