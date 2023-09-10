package com.example.demo.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Inventory;
import com.example.demo.entity.Rental;
import com.example.demo.entity.Staff;
import com.example.demo.exception.ObjectNotFoundException;
import com.example.demo.repo.CustomerRepo;
import com.example.demo.repo.InventoryRepo;
import com.example.demo.repo.RentalRepo;
import com.example.demo.repo.StaffRepo;
import com.example.demo.services.IRentalService;

@Service
public class RentalServiceImpl implements IRentalService {

    @Autowired
    private RentalRepo rentalRepo;

    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private StaffRepo staffRepo;
    @Autowired
    private InventoryRepo inventoryRepo;

    @Transactional(readOnly = true)
    @Override
    public List<Rental> findAll() {
        return rentalRepo.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Rental> findById(Integer id) {
        return rentalRepo.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean exist(Integer id) {
        return rentalRepo.existsById(id);
    }

    @Transactional(readOnly = false)
    @Override
    public Optional<Rental> insert(Rental saveRental) {

        Optional<Customer> oCustomer = customerRepo.findById(saveRental.getCustomer().getCustomerId());
        Optional<Staff> oStaff= staffRepo.findById(saveRental.getStaff().getStaffId());
        Optional<Inventory> oInventory = inventoryRepo.findById(saveRental.getInventory().getInventoryId());

        if(oCustomer.isPresent() && oStaff.isPresent() && oInventory.isPresent()) {
            Rental rental = new Rental();
            rental.setRentalId(null);
            rental.setRentalDate(saveRental.getRentalDate());
            rental.setInventory(oInventory.get());
            rental.setCustomer(oCustomer.get());
            rental.setReturnDate(saveRental.getReturnDate());
            rental.setStaff(oStaff.get());
            rental.setLastUpdate(saveRental.getLastUpdate());
            Rental o = rentalRepo.save(rental);
            return rentalRepo.findById(o.getRentalId());
        }
        throw new ObjectNotFoundException("Customer or Staff or Inventory are not exist.");
    }

    @Transactional(readOnly = false)
    @Override
    public Optional<Rental> update(Integer rentalId, Rental saveRental) {
        saveRental.setRentalId(rentalId);

        Optional<Customer> oCustomer = customerRepo.findById(saveRental.getCustomer().getCustomerId());
        Optional<Staff> oStaff= staffRepo.findById(saveRental.getStaff().getStaffId());
        Optional<Inventory> oInventory = inventoryRepo.findById(saveRental.getInventory().getInventoryId());

        Optional<Rental> rentalOptional = rentalRepo.findById(saveRental.getRentalId());
        if (rentalOptional.isPresent() && oCustomer.isPresent() && oStaff.isPresent() && oInventory.isPresent()) {
            Rental rental = rentalOptional.get();
            rental.setRentalDate(saveRental.getRentalDate());
            rental.setInventory(oInventory.get());
            rental.setCustomer(oCustomer.get());
            rental.setReturnDate(saveRental.getReturnDate());
            rental.setStaff(oStaff.get());
            rental.setLastUpdate(saveRental.getLastUpdate());
            Rental o = rentalRepo.save(rental);
            return rentalRepo.findById(o.getRentalId());
        }
        throw new ObjectNotFoundException("Customer or Staff or Inventory are not exist.");
    }
}

