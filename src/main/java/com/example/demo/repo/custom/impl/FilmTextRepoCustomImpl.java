package com.example.demo.repo.custom.impl;
import com.example.demo.entity.FilmText;
import com.example.demo.entity.QFilmText;
import com.example.demo.repo.custom.FilmTextRepoCustom;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import javax.persistence.EntityManager;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class FilmTextRepoCustomImpl implements FilmTextRepoCustom {
    private final JPAQueryFactory queryFactory ;
    private static final QFilmText qFilmText = QFilmText.filmText;

    public FilmTextRepoCustomImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<FilmText> dynamicSearch(FilmText filmText, Pageable page) {
        JPAQuery<FilmText> query =  queryFactory.selectFrom(qFilmText);
        if(filmText.getFilmId() != null ) {
            query.where(qFilmText.filmId.eq(filmText.getFilmId()));
        }
        if(filmText.getTitle() != null ) {
            query.where(qFilmText.title.eq(filmText.getTitle()));
        }
        if(filmText.getDescription() != null ) {
            query.where(qFilmText.description.eq(filmText.getDescription()));
        }
        if(page != null) {
            query.offset(page.getPageSize() * page.getPageNumber());
            query.limit(page.getPageSize());
        }
        return query.fetch();
    }
}
