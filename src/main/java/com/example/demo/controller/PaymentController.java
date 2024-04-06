package com.example.demo.controller;

import java.util.Optional;

import com.example.demo.base.BaseConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Payment;
import com.example.demo.services.IPaymentService;

@RestController
@RequestMapping(BaseConst.BASE_API + "/payment")
public class PaymentController {

    @Autowired
    private IPaymentService paymentService;

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        try {
            return ResponseEntity.ok(paymentService.findAll());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<?> findById(@PathVariable("paymentId") Integer paymentId) {
        try {
            Optional<Payment> oPayment = paymentService.findById(paymentId);
            if (oPayment.isPresent())
                return ResponseEntity.ok(oPayment.get());
            return new ResponseEntity<>("Payment is not exist", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> insert(@RequestBody Payment payment) {
        try {
            Optional<Payment> oPayment = paymentService.insert(payment);
            if (oPayment.isPresent())
                return ResponseEntity.ok(oPayment.get());
            return new ResponseEntity<>("Insert error", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Server error", HttpStatus.BAD_GATEWAY);
        }
    }

    @PutMapping("/{paymentId}")
    public ResponseEntity<?> update(@PathVariable("paymentId") Integer paymentId, @RequestBody Payment payment) {
        try {
            Optional<Payment> oPayment = paymentService.update(paymentId, payment);
            if (oPayment.isPresent())
                return ResponseEntity.ok(oPayment.get());
            return new ResponseEntity<>("Update error", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Server error", HttpStatus.BAD_GATEWAY);
        }
    }
}
