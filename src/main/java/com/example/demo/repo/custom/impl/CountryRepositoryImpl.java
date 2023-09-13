package com.example.demo.repo.custom.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import com.example.demo.entity.Country;
import com.example.demo.repo.custom.ICountryRepoCustom;

public class CountryRepositoryImpl implements ICountryRepoCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Country> getAllCustomByRangeId() {
        CriteriaQuery<Country> criteriaQuery = em.getCriteriaBuilder().createQuery(Country.class);
        criteriaQuery.select(criteriaQuery.from(Country.class));
        return em.createQuery(criteriaQuery).getResultList();
    }
}
