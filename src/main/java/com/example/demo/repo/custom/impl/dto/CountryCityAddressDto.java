package com.example.demo.repo.custom.impl.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CountryCityAddressDto {
    private Integer countryId;
    private String country;
    private Integer cityId;
    private String city;
    private Integer addressId;
    private String address;
}
