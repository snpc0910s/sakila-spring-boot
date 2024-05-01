package com.example.demo.repo.custom;
import java.util.List;
import org.springframework.data.domain.Pageable;
import com.example.demo.entity.Staff;

public interface StaffRepoCustom {
    public List<Staff> dynamicSearch(Staff staff, Pageable page);
}
