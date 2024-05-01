package com.example.demo.repo.custom;
import java.util.List;
import org.springframework.data.domain.Pageable;
import com.example.demo.entity.Country;

public interface CountryRepoCustom {
    public List<Country> dynamicSearch(Country country, Pageable page);
}
