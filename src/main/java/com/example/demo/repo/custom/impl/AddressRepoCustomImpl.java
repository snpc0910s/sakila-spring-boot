package com.example.demo.repo.custom.impl;
import com.example.demo.entity.Address;
import com.example.demo.entity.QAddress;
import com.example.demo.repo.custom.AddressRepoCustom;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import javax.persistence.EntityManager;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class AddressRepoCustomImpl implements AddressRepoCustom {
    private final JPAQueryFactory queryFactory ;
    private static final QAddress qAddress = QAddress.address1;

    public AddressRepoCustomImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<Address> dynamicSearch(Address address, Pageable page) {
        JPAQuery<Address> query =  queryFactory.selectFrom(qAddress);
        if(address.getAddressId() != null ) {
            query.where(qAddress.addressId.eq(address.getAddressId()));
        }
        if(address.getAddress() != null ) {
            query.where(qAddress.address.eq(address.getAddress()));
        }
        if(address.getAddress2() != null ) {
            query.where(qAddress.address2.eq(address.getAddress2()));
        }
        if(address.getDistrict() != null ) {
            query.where(qAddress.district.eq(address.getDistrict()));
        }
        if(address.getPostalCode() != null ) {
            query.where(qAddress.postalCode.eq(address.getPostalCode()));
        }
        if(address.getPhone() != null ) {
            query.where(qAddress.phone.eq(address.getPhone()));
        }
        if(address.getLocation() != null ) {
            query.where(qAddress.location.eq(address.getLocation()));
        }
        if(address.getLastUpdate() != null ) {
            query.where(qAddress.lastUpdate.eq(address.getLastUpdate()));
        }
        if(page != null) {
            query.offset(page.getPageSize() * page.getPageNumber());
            query.limit(page.getPageSize());
        }
        return query.fetch();
    }
}
