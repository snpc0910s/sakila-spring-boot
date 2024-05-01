package com.example.demo.repo.custom.impl;
import com.example.demo.entity.Staff;
import com.example.demo.entity.QStaff;
import com.example.demo.repo.custom.StaffRepoCustom;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import javax.persistence.EntityManager;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class StaffRepoCustomImpl implements StaffRepoCustom {
    private final JPAQueryFactory queryFactory ;
    private static final QStaff qStaff = QStaff.staff;

    public StaffRepoCustomImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<Staff> dynamicSearch(Staff staff, Pageable page) {
        JPAQuery<Staff> query =  queryFactory.selectFrom(qStaff);
        if(staff.getStaffId() != null ) {
            query.where(qStaff.staffId.eq(staff.getStaffId()));
        }
        if(staff.getFirstName() != null ) {
            query.where(qStaff.firstName.eq(staff.getFirstName()));
        }
        if(staff.getLastName() != null ) {
            query.where(qStaff.lastName.eq(staff.getLastName()));
        }
        if(staff.getPicture() != null ) {
            query.where(qStaff.picture.eq(staff.getPicture()));
        }
        if(staff.getEmail() != null ) {
            query.where(qStaff.email.eq(staff.getEmail()));
        }
        if(staff.getActive() != null ) {
            query.where(qStaff.active.eq(staff.getActive()));
        }
        if(staff.getUsername() != null ) {
            query.where(qStaff.username.eq(staff.getUsername()));
        }
        if(staff.getPassword() != null ) {
            query.where(qStaff.password.eq(staff.getPassword()));
        }
        if(staff.getLastUpdate() != null ) {
            query.where(qStaff.lastUpdate.eq(staff.getLastUpdate()));
        }
        if(page != null) {
            query.offset(page.getPageSize() * page.getPageNumber());
            query.limit(page.getPageSize());
        }
        return query.fetch();
    }
}
