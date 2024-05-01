package com.example.demo.repo.custom.impl;
import com.example.demo.entity.Language;
import com.example.demo.entity.QLanguage;
import com.example.demo.repo.custom.LanguageRepoCustom;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import javax.persistence.EntityManager;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class LanguageRepoCustomImpl implements LanguageRepoCustom {
    private final JPAQueryFactory queryFactory ;
    private static final QLanguage qLanguage = QLanguage.language;

    public LanguageRepoCustomImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<Language> dynamicSearch(Language language, Pageable page) {
        JPAQuery<Language> query =  queryFactory.selectFrom(qLanguage);
        if(language.getLanguageId() != null ) {
            query.where(qLanguage.languageId.eq(language.getLanguageId()));
        }
        if(language.getName() != null ) {
            query.where(qLanguage.name.eq(language.getName()));
        }
        if(language.getLastUpdate() != null ) {
            query.where(qLanguage.lastUpdate.eq(language.getLastUpdate()));
        }
        if(page != null) {
            query.offset(page.getPageSize() * page.getPageNumber());
            query.limit(page.getPageSize());
        }
        return query.fetch();
    }
}
