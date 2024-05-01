package com.example.demo.repo.custom.impl;
import com.example.demo.entity.Store;
import com.example.demo.entity.QStore;
import com.example.demo.repo.custom.StoreRepoCustom;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import javax.persistence.EntityManager;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class StoreRepoCustomImpl implements StoreRepoCustom {
    private final JPAQueryFactory queryFactory ;
    private static final QStore qStore = QStore.store;

    public StoreRepoCustomImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<Store> dynamicSearch(Store store, Pageable page) {
        JPAQuery<Store> query =  queryFactory.selectFrom(qStore);
        if(store.getStoreId() != null ) {
            query.where(qStore.storeId.eq(store.getStoreId()));
        }
        if(store.getLastUpdate() != null ) {
            query.where(qStore.lastUpdate.eq(store.getLastUpdate()));
        }
        if(page != null) {
            query.offset(page.getPageSize() * page.getPageNumber());
            query.limit(page.getPageSize());
        }
        return query.fetch();
    }
}
