package com.example.demo.repo.custom.impl;
import com.example.demo.entity.Inventory;
import com.example.demo.entity.QInventory;
import com.example.demo.repo.custom.InventoryRepoCustom;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import javax.persistence.EntityManager;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class InventoryRepoCustomImpl implements InventoryRepoCustom {
    private final JPAQueryFactory queryFactory ;
    private static final QInventory qInventory = QInventory.inventory;

    public InventoryRepoCustomImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<Inventory> dynamicSearch(Inventory inventory, Pageable page) {
        JPAQuery<Inventory> query =  queryFactory.selectFrom(qInventory);
        if(inventory.getInventoryId() != null ) {
            query.where(qInventory.inventoryId.eq(inventory.getInventoryId()));
        }
        if(inventory.getLastUpdate() != null ) {
            query.where(qInventory.lastUpdate.eq(inventory.getLastUpdate()));
        }
        if(page != null) {
            query.offset(page.getPageSize() * page.getPageNumber());
            query.limit(page.getPageSize());
        }
        return query.fetch();
    }
}
