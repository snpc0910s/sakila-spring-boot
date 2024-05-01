package com.example.demo.repo.custom.impl;
import com.example.demo.entity.Rental;
import com.example.demo.entity.QRental;
import com.example.demo.repo.custom.RentalRepoCustom;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import javax.persistence.EntityManager;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class RentalRepoCustomImpl implements RentalRepoCustom {
    private final JPAQueryFactory queryFactory ;
    private static final QRental qRental = QRental.rental;

    public RentalRepoCustomImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<Rental> dynamicSearch(Rental rental, Pageable page) {
        JPAQuery<Rental> query =  queryFactory.selectFrom(qRental);
        if(rental.getRentalId() != null ) {
            query.where(qRental.rentalId.eq(rental.getRentalId()));
        }
        if(rental.getRentalDate() != null ) {
            query.where(qRental.rentalDate.eq(rental.getRentalDate()));
        }
        if(rental.getReturnDate() != null ) {
            query.where(qRental.returnDate.eq(rental.getReturnDate()));
        }
        if(rental.getLastUpdate() != null ) {
            query.where(qRental.lastUpdate.eq(rental.getLastUpdate()));
        }
        if(page != null) {
            query.offset(page.getPageSize() * page.getPageNumber());
            query.limit(page.getPageSize());
        }
        return query.fetch();
    }
}
