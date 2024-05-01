package com.example.demo.repo.custom.impl;
import com.example.demo.entity.Actor;
import com.example.demo.entity.QActor;
import com.example.demo.repo.custom.ActorRepoCustom;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import javax.persistence.EntityManager;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ActorRepoCustomImpl implements ActorRepoCustom {
    private final JPAQueryFactory queryFactory ;
    private static final QActor qActor = QActor.actor;

    public ActorRepoCustomImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<Actor> dynamicSearch(Actor actor, Pageable page) {
        JPAQuery<Actor> query =  queryFactory.selectFrom(qActor);
        if(actor.getActorId() != null ) {
            query.where(qActor.actorId.eq(actor.getActorId()));
        }
        if(actor.getFirstName() != null ) {
            query.where(qActor.firstName.eq(actor.getFirstName()));
        }
        if(actor.getLastName() != null ) {
            query.where(qActor.lastName.eq(actor.getLastName()));
        }
        if(actor.getLastUpdate() != null ) {
            query.where(qActor.lastUpdate.eq(actor.getLastUpdate()));
        }
        if(page != null) {
            query.offset(page.getPageSize() * page.getPageNumber());
            query.limit(page.getPageSize());
        }
        return query.fetch();
    }
}
