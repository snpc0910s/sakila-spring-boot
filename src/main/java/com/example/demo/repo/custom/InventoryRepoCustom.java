package com.example.demo.repo.custom;
import java.util.List;
import org.springframework.data.domain.Pageable;
import com.example.demo.entity.Inventory;

public interface InventoryRepoCustom {
    public List<Inventory> dynamicSearch(Inventory inventory, Pageable page);
}
