package com.example.demo.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Address;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Store;
import com.example.demo.exception.ObjectNotFoundException;
import com.example.demo.repo.AddressRepo;
import com.example.demo.repo.CustomerRepo;
import com.example.demo.repo.StoreRepo;
import com.example.demo.services.ICustomerService;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private StoreRepo storeRepo;

    @Autowired
    private AddressRepo addressRepo;

    @Transactional(readOnly = true)
    @Override
    public List<Customer> findAll() {
        return customerRepo.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Customer> findById(Integer id) {
        return customerRepo.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean exist(Integer id) {
        return customerRepo.existsById(id);
    }

    @Transactional(readOnly = false)
    @Override
    public Optional<Customer> insert(Customer saveCustomer) {
        Optional<Store> oStore = storeRepo.findById(saveCustomer.getStore().getStoreId());
        Optional<Address> oAddress = addressRepo.findById(saveCustomer.getAddress().getAddressId());
        if(oStore.isPresent() && oAddress.isPresent()) {
                Customer customer = new Customer();
                customer.setCustomerId(null);
                customer.setStore(oStore.get());
                customer.setFirstName(saveCustomer.getFirstName());
                customer.setLastName(saveCustomer.getLastName());
                customer.setEmail(saveCustomer.getEmail());
                customer.setAddress(oAddress.get());
                customer.setActive(saveCustomer.getActive());
                customer.setCreateDate(saveCustomer.getCreateDate());
                customer.setLastUpdate(saveCustomer.getLastUpdate());
                Customer o = customerRepo.save(customer);
                return customerRepo.findById(o.getCustomerId());
        }
        throw new ObjectNotFoundException("Not exist Store or Address");
    }

    @Transactional(readOnly = false)
    @Override
    public Optional<Customer> update(Integer customerId, Customer saveCustomer) {
        saveCustomer.setCustomerId(customerId);
        Optional<Customer> customerOptional = customerRepo.findById(saveCustomer.getCustomerId());
        Optional<Store> oStore = storeRepo.findById(saveCustomer.getStore().getStoreId());
        Optional<Address> oAddress = addressRepo.findById(saveCustomer.getAddress().getAddressId());
        if (customerOptional.isPresent() && oStore.isPresent() && oAddress.isPresent()) {
            Customer customer = customerOptional.get();
            customer.setStore(oStore.get());
            customer.setFirstName(saveCustomer.getFirstName());
            customer.setLastName(saveCustomer.getLastName());
            customer.setEmail(saveCustomer.getEmail());
            customer.setAddress(oAddress.get());
            customer.setActive(saveCustomer.getActive());
            customer.setCreateDate(saveCustomer.getCreateDate());
            customer.setLastUpdate(saveCustomer.getLastUpdate());
            Customer o = customerRepo.save(customer);
            return customerRepo.findById(o.getCustomerId());
        }
        return Optional.empty();
    }
}

