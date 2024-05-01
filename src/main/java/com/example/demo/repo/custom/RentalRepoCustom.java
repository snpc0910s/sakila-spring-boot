package com.example.demo.repo.custom;
import java.util.List;
import org.springframework.data.domain.Pageable;
import com.example.demo.entity.Rental;

public interface RentalRepoCustom {
    public List<Rental> dynamicSearch(Rental rental, Pageable page);
}
