package com.example.demo.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Payment;
import com.example.demo.entity.Rental;
import com.example.demo.entity.Staff;
import com.example.demo.repo.CustomerRepo;
import com.example.demo.repo.PaymentRepo;
import com.example.demo.repo.RentalRepo;
import com.example.demo.repo.StaffRepo;
import com.example.demo.services.IPaymentService;

@Service
public class PaymentServiceImpl implements IPaymentService {

    @Autowired
    private PaymentRepo paymentRepo;
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private StaffRepo staffRepo;
    @Autowired
    private RentalRepo rentalRepo;

    @Transactional(readOnly = true)
    @Override
    public List<Payment> findAll() {
        return paymentRepo.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Payment> findById(Integer id) {
        return paymentRepo.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean exist(Integer id) {
        return paymentRepo.existsById(id);
    }

    @Transactional(readOnly = false)
    @Override
    public Optional<Payment> insert(Payment savePayment) {
        Optional<Customer> oCustomer = customerRepo.findById(savePayment.getCustomer().getCustomerId());
        Optional<Staff> oStaff= staffRepo.findById(savePayment.getStaff().getStaffId());
        Optional<Rental> oRetal = rentalRepo.findById(savePayment.getRental().getRentalId());

        Payment payment = new Payment();
        payment.setPaymentId(null);
        payment.setCustomer(oCustomer.get());
        payment.setStaff(oStaff.get());
        payment.setRental(oRetal.get());
        payment.setAmount(savePayment.getAmount());
        payment.setPaymentDate(savePayment.getPaymentDate());
        payment.setLastUpdate(savePayment.getLastUpdate());
        Payment o = paymentRepo.save(payment);
        return paymentRepo.findById(o.getPaymentId());
    }

    @Transactional(readOnly = false)
    @Override
    public Optional<Payment> update(Integer paymentId, Payment savePayment) {
        savePayment.setPaymentId(paymentId);
        Optional<Customer> oCustomer = customerRepo.findById(savePayment.getCustomer().getCustomerId());
        Optional<Staff> oStaff= staffRepo.findById(savePayment.getStaff().getStaffId());
        Optional<Rental> oRetal = rentalRepo.findById(savePayment.getRental().getRentalId());

        Optional<Payment> paymentOptional = paymentRepo.findById(savePayment.getPaymentId());
        if (paymentOptional.isPresent() 
                && oCustomer.isPresent()
                && oStaff.isPresent()
                && oRetal.isPresent())  {
            Payment payment = paymentOptional.get();
            payment.setCustomer(oCustomer.get());
            payment.setStaff(oStaff.get());
            payment.setRental(oRetal.get());
            payment.setAmount(savePayment.getAmount());
            payment.setPaymentDate(savePayment.getPaymentDate());
            payment.setLastUpdate(savePayment.getLastUpdate());
            Payment o = paymentRepo.save(payment);
            return paymentRepo.findById(o.getPaymentId());
        }
        return Optional.empty();
    }
}
