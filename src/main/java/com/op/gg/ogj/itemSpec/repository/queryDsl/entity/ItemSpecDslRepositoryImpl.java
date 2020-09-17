package com.op.gg.ogj.itemSpec.repository.queryDsl.entity;

import com.op.gg.ogj.character.model.entity.Character;
import com.op.gg.ogj.itemSpec.model.QItemSpec;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static com.op.gg.ogj.itemSpec.model.QItemSpec.itemSpec;


public class ItemSpecDslRepositoryImpl implements ItemSpecDslRepository{

    private final JPAQueryFactory jpaQueryFactory;

    public ItemSpecDslRepositoryImpl(EntityManager entityManager){
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    }


    @Override
    public void updateItemSpecByItemSpecIds(List<Long> itemSpecIds) {
        jpaQueryFactory.update(itemSpec)
                .set(itemSpec.actYn, false)
                .where(whereItemSpecIds(itemSpecIds))
                .execute();
    }

    public BooleanExpression whereItemSpecIds(List<Long> itemSpecIds){ return itemSpecIds==null||itemSpecIds.isEmpty()?null:itemSpec.itemSpecId.in(itemSpecIds); }

}
