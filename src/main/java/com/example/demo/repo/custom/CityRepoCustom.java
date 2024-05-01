package com.example.demo.repo.custom;
import java.util.List;
import org.springframework.data.domain.Pageable;
import com.example.demo.entity.City;

public interface CityRepoCustom {
    public List<City> dynamicSearch(City city, Pageable page);
}
