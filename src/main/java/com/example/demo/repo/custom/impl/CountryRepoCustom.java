package com.example.demo.repo.custom.impl;

// import java.util.ArrayList;
// import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
// import javax.persistence.TypedQuery;
// import javax.persistence.criteria.CriteriaBuilder;
// import javax.persistence.criteria.CriteriaQuery;
// import javax.persistence.criteria.Root;

// import org.springframework.stereotype.Component;
// import org.springframework.stereotype.Repository;

// import com.example.demo.entity.Country;
import com.example.demo.repo.custom.ICountryRepoCustom;

public class CountryRepoCustom implements ICountryRepoCustom {
    
    @PersistenceContext
    private EntityManager entityManager;

    // @Override
    // public List<Country> getAllCustomByRangeId(Integer idStart, Integer idEnd) {
    //     CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    //     CriteriaQuery<Country> query = cb.createQuery(Country.class);
    //     Root<Country> c = query.from(Country.class);
    //     query.select(c);
    //     query.where(cb.between(c.get("countryId"), idStart, idEnd));
    //     TypedQuery<Country> typedQuery = entityManager.createQuery(query);
    //     return typedQuery.getResultList();
    // }
    // @Override
    // public String something(Integer start, Integer end) {
    //     return "Hello";
    // }
}
