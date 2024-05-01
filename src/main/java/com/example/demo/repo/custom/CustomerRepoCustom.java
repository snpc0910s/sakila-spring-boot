package com.example.demo.repo.custom;
import java.util.List;
import org.springframework.data.domain.Pageable;
import com.example.demo.entity.Customer;

public interface CustomerRepoCustom {
    public List<Customer> dynamicSearch(Customer customer, Pageable page);
}
