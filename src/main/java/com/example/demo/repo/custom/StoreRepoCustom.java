package com.example.demo.repo.custom;
import java.util.List;
import org.springframework.data.domain.Pageable;
import com.example.demo.entity.Store;

public interface StoreRepoCustom {
    public List<Store> dynamicSearch(Store store, Pageable page);
}
