package com.example.demo.repo.custom.impl;
import com.example.demo.entity.Category;
import com.example.demo.entity.QCategory;
import com.example.demo.repo.custom.CategoryRepoCustom;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import javax.persistence.EntityManager;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class CategoryRepoCustomImpl implements CategoryRepoCustom {
    private final JPAQueryFactory queryFactory ;
    private static final QCategory qCategory = QCategory.category;

    public CategoryRepoCustomImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<Category> dynamicSearch(Category category, Pageable page) {
        JPAQuery<Category> query =  queryFactory.selectFrom(qCategory);
        if(category.getCategoryId() != null ) {
            query.where(qCategory.categoryId.eq(category.getCategoryId()));
        }
        if(category.getName() != null ) {
            query.where(qCategory.name.eq(category.getName()));
        }
        if(category.getLastUpdate() != null ) {
            query.where(qCategory.lastUpdate.eq(category.getLastUpdate()));
        }
        if(page != null) {
            query.offset(page.getPageSize() * page.getPageNumber());
            query.limit(page.getPageSize());
        }
        return query.fetch();
    }
}
