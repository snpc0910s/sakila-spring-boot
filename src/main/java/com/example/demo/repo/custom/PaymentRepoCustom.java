package com.example.demo.repo.custom;
import java.util.List;
import org.springframework.data.domain.Pageable;
import com.example.demo.entity.Payment;

public interface PaymentRepoCustom {
    public List<Payment> dynamicSearch(Payment payment, Pageable page);
}
