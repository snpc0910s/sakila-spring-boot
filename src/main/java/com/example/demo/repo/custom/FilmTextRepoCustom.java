package com.example.demo.repo.custom;
import java.util.List;
import org.springframework.data.domain.Pageable;
import com.example.demo.entity.FilmText;

public interface FilmTextRepoCustom {
    public List<FilmText> dynamicSearch(FilmText filmText, Pageable page);
}
