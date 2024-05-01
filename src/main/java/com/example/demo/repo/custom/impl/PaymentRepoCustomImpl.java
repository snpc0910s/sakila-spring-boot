package com.example.demo.repo.custom.impl;
import com.example.demo.entity.Payment;
import com.example.demo.entity.QPayment;
import com.example.demo.repo.custom.PaymentRepoCustom;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import javax.persistence.EntityManager;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class PaymentRepoCustomImpl implements PaymentRepoCustom {
    private final JPAQueryFactory queryFactory ;
    private static final QPayment qPayment = QPayment.payment;

    public PaymentRepoCustomImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<Payment> dynamicSearch(Payment payment, Pageable page) {
        JPAQuery<Payment> query =  queryFactory.selectFrom(qPayment);
        if(payment.getPaymentId() != null ) {
            query.where(qPayment.paymentId.eq(payment.getPaymentId()));
        }
        if(payment.getAmount() != null ) {
            query.where(qPayment.amount.eq(payment.getAmount()));
        }
        if(payment.getPaymentDate() != null ) {
            query.where(qPayment.paymentDate.eq(payment.getPaymentDate()));
        }
        if(payment.getLastUpdate() != null ) {
            query.where(qPayment.lastUpdate.eq(payment.getLastUpdate()));
        }
        if(page != null) {
            query.offset(page.getPageSize() * page.getPageNumber());
            query.limit(page.getPageSize());
        }
        return query.fetch();
    }
}
