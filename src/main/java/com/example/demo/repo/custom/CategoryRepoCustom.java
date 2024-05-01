package com.example.demo.repo.custom;
import java.util.List;
import org.springframework.data.domain.Pageable;
import com.example.demo.entity.Category;

public interface CategoryRepoCustom {
    public List<Category> dynamicSearch(Category category, Pageable page);
}
