package com.example.demo.repo.custom.impl;

import com.example.demo.entity.Country;
import com.example.demo.entity.QAddress;
import com.example.demo.entity.QCity;
import com.example.demo.entity.QCountry;
import com.example.demo.repo.custom.ICountryRepoCustom;
import com.example.demo.repo.custom.impl.dto.CountryCityAddressDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class CountryRepositoryImpl implements ICountryRepoCustom {

    private final EntityManager entityManager;
    private final JPAQueryFactory queryFactory ;

    public CountryRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<Country> getListCustomByRangeId() {
        CriteriaQuery<Country> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(Country.class);
        criteriaQuery.select(criteriaQuery.from(Country.class));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
    @Override
    public List<CountryCityAddressDto> getListByCityNameDSQL(String cityName) {
        QCountry country = QCountry.country1;
        QCity city = QCity.city1;
        QAddress address = QAddress.address1;
        return queryFactory
                .select(Projections.constructor(CountryCityAddressDto.class,country.countryId, country.country, city.cityId, city.city, address.addressId, address.address))
                .from(country)
                .innerJoin(country.lstCity, city)
                .innerJoin(city.lstAddress,address)
                .where(address.address.contains(cityName))
                .fetch();
//        return queryFactory.selectFrom(country)
//                .innerJoin(country.lstCity, city)
//                .innerJoin(city.lstAddress,address)
//                .where(address.address.contains(cityName))
//                .fetch();
    }
}
