package com.example.demo.repo.custom.impl;
import com.example.demo.entity.Country;
import com.example.demo.entity.QCountry;
import com.example.demo.repo.custom.CountryRepoCustom;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import javax.persistence.EntityManager;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class CountryRepoCustomImpl implements CountryRepoCustom {
    private final JPAQueryFactory queryFactory ;
    private static final QCountry qCountry = QCountry.country1;

    public CountryRepoCustomImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<Country> dynamicSearch(Country country, Pageable page) {
        JPAQuery<Country> query =  queryFactory.selectFrom(qCountry);
        if(country.getCountryId() != null ) {
            query.where(qCountry.countryId.eq(country.getCountryId()));
        }
        if(country.getCountry() != null ) {
            query.where(qCountry.country.eq(country.getCountry()));
        }
        if(country.getLastUpdate() != null ) {
            query.where(qCountry.lastUpdate.eq(country.getLastUpdate()));
        }
        if(page != null) {
            query.offset(page.getPageSize() * page.getPageNumber());
            query.limit(page.getPageSize());
        }
        return query.fetch();
    }
}
