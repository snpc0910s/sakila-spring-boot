package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Payment;

public interface IPaymentService {
    public List<Payment> findAll();

    public Optional<Payment> findById(Integer id);

    public boolean exist(Integer id);

    public Optional<Payment> insert(Payment savePayment);

    public Optional<Payment> update(Integer paymentId, Payment savePayment);
}

