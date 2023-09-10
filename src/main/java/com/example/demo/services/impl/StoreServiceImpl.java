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
import com.example.demo.services.IStoreService;

@Service
public class StoreServiceImpl implements IStoreService {

    @Autowired
    private StoreRepo storeRepo;

    @Autowired
    private StaffRepo staffRepo;

    @Autowired
    private AddressRepo addressRepo;

    @Transactional(readOnly = true)
    @Override
    public List<Store> findAll() {
        return storeRepo.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Store> findById(Integer id) {
        return storeRepo.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean exist(Integer id) {
        return storeRepo.existsById(id);
    }

    @Transactional(readOnly = false)
    @Override
    public Optional<Store> insert(Store saveStore) {
        Optional<Staff> oManagerStaff = staffRepo.findById(saveStore.getManagerStaff().getStaffId());
        Optional<Address> oAddress = addressRepo.findById(saveStore.getAddress().getAddressId());

        if (oManagerStaff.isPresent() && oAddress.isPresent()) {
            Store store = new Store();
            store.setStoreId(null);
            store.setManagerStaff(oManagerStaff.get());
            store.setAddress(oAddress.get());
            store.setLastUpdate(saveStore.getLastUpdate());
            Store o = storeRepo.save(store);
            return storeRepo.findById(o.getStoreId());
        }
        throw new ObjectNotFoundException("Staff or Address is not exist.");
    }

    @Transactional(readOnly = false)
    @Override
    public Optional<Store> update(Integer storeId, Store saveStore) {
        saveStore.setStoreId(storeId);
        Optional<Staff> oManagerStaff = staffRepo.findById(saveStore.getManagerStaff().getStaffId());
        Optional<Address> oAddress = addressRepo.findById(saveStore.getAddress().getAddressId());

        Optional<Store> storeOptional = storeRepo.findById(saveStore.getStoreId());
        if (storeOptional.isPresent() && oManagerStaff.isPresent() && oAddress.isPresent()) {
            Store store = storeOptional.get();
            store.setManagerStaff(oManagerStaff.get());
            store.setAddress(oAddress.get());
            store.setLastUpdate(saveStore.getLastUpdate());
            Store o = storeRepo.save(store);
            return storeRepo.findById(o.getStoreId());
        }
        throw new ObjectNotFoundException("Staff or Address is not exist.");
    }
}
