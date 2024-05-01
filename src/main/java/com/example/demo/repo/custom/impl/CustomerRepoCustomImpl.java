package com.example.demo.repo.custom.impl;
import com.example.demo.entity.Customer;
import com.example.demo.entity.QCustomer;
import com.example.demo.repo.custom.CustomerRepoCustom;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import javax.persistence.EntityManager;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class CustomerRepoCustomImpl implements CustomerRepoCustom {
    private final JPAQueryFactory queryFactory ;
    private static final QCustomer qCustomer = QCustomer.customer;

    public CustomerRepoCustomImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<Customer> dynamicSearch(Customer customer, Pageable page) {
        JPAQuery<Customer> query =  queryFactory.selectFrom(qCustomer);
        if(customer.getCustomerId() != null ) {
            query.where(qCustomer.customerId.eq(customer.getCustomerId()));
        }
        if(customer.getFirstName() != null ) {
            query.where(qCustomer.firstName.eq(customer.getFirstName()));
        }
        if(customer.getLastName() != null ) {
            query.where(qCustomer.lastName.eq(customer.getLastName()));
        }
        if(customer.getEmail() != null ) {
            query.where(qCustomer.email.eq(customer.getEmail()));
        }
        if(customer.getActive() != null ) {
            query.where(qCustomer.active.eq(customer.getActive()));
        }
        if(customer.getCreateDate() != null ) {
            query.where(qCustomer.createDate.eq(customer.getCreateDate()));
        }
        if(customer.getLastUpdate() != null ) {
            query.where(qCustomer.lastUpdate.eq(customer.getLastUpdate()));
        }
        if(page != null) {
            query.offset(page.getPageSize() * page.getPageNumber());
            query.limit(page.getPageSize());
        }
        return query.fetch();
    }
}
