package com.example.demo.repo.custom;
import java.util.List;
import org.springframework.data.domain.Pageable;
import com.example.demo.entity.Language;

public interface LanguageRepoCustom {
    public List<Language> dynamicSearch(Language language, Pageable page);
}
