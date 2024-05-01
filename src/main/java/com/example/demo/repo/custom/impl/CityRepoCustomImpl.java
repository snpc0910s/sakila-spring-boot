package com.example.demo.repo.custom.impl;
import com.example.demo.entity.City;
import com.example.demo.entity.QCity;
import com.example.demo.repo.custom.CityRepoCustom;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import javax.persistence.EntityManager;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class CityRepoCustomImpl implements CityRepoCustom {
    private final JPAQueryFactory queryFactory ;
    private static final QCity qCity = QCity.city1;

    public CityRepoCustomImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<City> dynamicSearch(City city, Pageable page) {
        JPAQuery<City> query =  queryFactory.selectFrom(qCity);
        if(city.getCityId() != null ) {
            query.where(qCity.cityId.eq(city.getCityId()));
        }
        if(city.getCity() != null ) {
            query.where(qCity.city.eq(city.getCity()));
        }
        if(city.getLastUpdate() != null ) {
            query.where(qCity.lastUpdate.eq(city.getLastUpdate()));
        }
        if(page != null) {
            query.offset(page.getPageSize() * page.getPageNumber());
            query.limit(page.getPageSize());
        }
        return query.fetch();
    }
}
