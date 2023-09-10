package com.example.demo.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Address;
import com.example.demo.entity.City;
import com.example.demo.exception.ObjectNotFoundException;
import com.example.demo.repo.AddressRepo;
import com.example.demo.repo.CityRepo;
import com.example.demo.services.IAddressService;

@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private AddressRepo addressRepo;

    @Autowired
    private CityRepo cityRepo;

    @Transactional(readOnly = true)
    @Override
    public List<Address> findAll() {
        return addressRepo.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Address> findById(Integer id) {
        return addressRepo.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean exist(Integer id) {
        return addressRepo.existsById(id);
    }

    @Transactional(readOnly = false)
    @Override
    public Optional<Address> insert(Address saveAddress) {
        Optional<City> oCity = cityRepo.findById(saveAddress.getCity().getCityId());
        if(oCity.isPresent()) {
            Address address = new Address();
            address.setAddressId(null);
            address.setAddress(saveAddress.getAddress());
            address.setAddress2(saveAddress.getAddress2());
            address.setDistrict(saveAddress.getDistrict());
            address.setCity(oCity.get());
            address.setPostalCode(saveAddress.getPostalCode());
            address.setPhone(saveAddress.getPhone());
            address.setLocation(saveAddress.getLocation());
            address.setLastUpdate(saveAddress.getLastUpdate());
            Address o = addressRepo.save(address);
            return addressRepo.findById(o.getAddressId());
        }
        throw new ObjectNotFoundException("City is not exist.");
    }

    @Transactional(readOnly = false)
    @Override
    public Optional<Address> update(Integer addressId, Address saveAddress) {
        saveAddress.setAddressId(addressId);
        Optional<Address> addressOptional = addressRepo.findById(saveAddress.getAddressId());
        Optional<City> oCity = cityRepo.findById(saveAddress.getCity().getCityId());
        if (addressOptional.isPresent() && oCity.isPresent()) {
            Address address = addressOptional.get();
            address.setAddress(saveAddress.getAddress());
            address.setAddress2(saveAddress.getAddress2());
            address.setDistrict(saveAddress.getDistrict());
             address.setCity(oCity.get());
            address.setPostalCode(saveAddress.getPostalCode());
            address.setPhone(saveAddress.getPhone());
            address.setLocation(saveAddress.getLocation());
            address.setLastUpdate(saveAddress.getLastUpdate());
            Address o = addressRepo.save(address);
            return addressRepo.findById(o.getAddressId());
        }
        return Optional.empty();
    }
}

