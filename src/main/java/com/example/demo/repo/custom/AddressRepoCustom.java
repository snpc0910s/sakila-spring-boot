package com.example.demo.repo.custom;
import java.util.List;
import org.springframework.data.domain.Pageable;
import com.example.demo.entity.Address;

public interface AddressRepoCustom {
    public List<Address> dynamicSearch(Address address, Pageable page);
}
