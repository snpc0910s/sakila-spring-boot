package com.example.demo.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Address;
import com.example.demo.entity.Staff;
import com.example.demo.entity.Store;
import com.example.demo.exception.ObjectNotFoundException;
import com.example.demo.repo.AddressRepo;
import com.example.demo.repo.StaffRepo;
import com.example.demo.repo.StoreRepo;
import com.example.demo.services.IStaffService;

@Service
public class StaffServiceImpl implements IStaffService {

    @Autowired
    private StaffRepo staffRepo;
    @Autowired
    private AddressRepo addressRepo;
    @Autowired
    private StoreRepo storeRepo;

    @Transactional(readOnly = true)
    @Override
    public List<Staff> findAll() {
        return staffRepo.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Staff> findById(Integer id) {
        return staffRepo.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean exist(Integer id) {
        return staffRepo.existsById(id);
    }

    @Transactional(readOnly = false)
    @Override
    public Optional<Staff> insert(Staff saveStaff) {
        Optional<Address> oAddress = addressRepo.findById(saveStaff.getAddress().getAddressId());
        Optional<Store> oStore = storeRepo.findById(saveStaff.getStore().getStoreId());

        if (oAddress.isPresent() && oStore.isPresent()) {
            Staff staff = new Staff();
            staff.setStaffId(null);
            staff.setFirstName(saveStaff.getFirstName());
            staff.setLastName(saveStaff.getLastName());
            staff.setAddress(oAddress.get());
            staff.setPicture(saveStaff.getPicture());
            staff.setEmail(saveStaff.getEmail());
            staff.setStore(oStore.get());
            staff.setActive(saveStaff.getActive());
            staff.setUsername(saveStaff.getUsername());
            staff.setPassword(saveStaff.getPassword());
            staff.setLastUpdate(saveStaff.getLastUpdate());
            Staff o = staffRepo.save(staff);
            return staffRepo.findById(o.getStaffId());
        }
        throw new ObjectNotFoundException("Address or Store are not exist.");
    }

    @Transactional(readOnly = false)
    @Override
    public Optional<Staff> update(Integer staffId, Staff saveStaff) {
        saveStaff.setStaffId(staffId);
        Optional<Address> oAddress = addressRepo.findById(saveStaff.getAddress().getAddressId());
        Optional<Store> oStore = storeRepo.findById(saveStaff.getStore().getStoreId());
        Optional<Staff> staffOptional = staffRepo.findById(saveStaff.getStaffId());
        if (staffOptional.isPresent() && oAddress.isPresent() && oStore.isPresent()) {
            Staff staff = staffOptional.get();
            staff.setFirstName(saveStaff.getFirstName());
            staff.setLastName(saveStaff.getLastName());
            staff.setAddress(oAddress.get());
            staff.setPicture(saveStaff.getPicture());
            staff.setEmail(saveStaff.getEmail());
            staff.setStore(oStore.get());
            staff.setActive(saveStaff.getActive());
            staff.setUsername(saveStaff.getUsername());
            staff.setPassword(saveStaff.getPassword());
            staff.setLastUpdate(saveStaff.getLastUpdate());
            Staff o = staffRepo.save(staff);
            return staffRepo.findById(o.getStaffId());
        }
        throw new ObjectNotFoundException("Address or Store are not exist.");
    }
}
