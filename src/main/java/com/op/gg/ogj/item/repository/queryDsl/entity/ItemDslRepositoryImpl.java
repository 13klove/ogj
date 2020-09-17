package com.op.gg.ogj.item.repository.queryDsl.entity;

import com.op.gg.ogj.item.model.entity.Item;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

import static com.op.gg.ogj.character.model.entity.QCharacter.character;
import static com.op.gg.ogj.item.model.entity.QItem.item;
import static com.op.gg.ogj.itemSpec.model.QItemSpec.itemSpec;

public class ItemDslRepositoryImpl implements ItemDslRepository{

    private final JPAQueryFactory jpaQueryFactory;

    public ItemDslRepositoryImpl(EntityManager entityManager){
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Item findItemByItemIdAcUpdate(Long itemId) {
        return jpaQueryFactory.select(item)
                .from(item)
                .join(item.character, character).fetchJoin()
                .join(item.itemSpec, itemSpec).fetchJoin()
                .where(whereItemId(itemId), whereActYn(true))
                .fetchOne();
    }

    @Override
    public Item findDelItemByItemId(Long itemId) {
        return jpaQueryFactory.select(item)
                .from(item)
                .join(item.character, character).fetchJoin()
                .join(item.itemSpec, itemSpec).fetchJoin()
                .where(whereItemId(itemId), whereActYn(true))
                .fetchOne();
    }

    @Override
    public List<Item> findDelItemsByItemIds(List<Long> itemIds) {
        return jpaQueryFactory.select(item)
                .from(item)
                .join(item.character, character).fetchJoin()
                .join(item.itemSpec, itemSpec).fetchJoin()
                .where(whereItemIds(itemIds), whereActYn(true))
                .fetch();
    }

    @Override
    public void updateItemActYn(List<Long> characterIds) {
        jpaQueryFactory.update(item)
                .set(item.actYn, false)
                .where(whereCharacterIds(characterIds))
                .execute();
    }

    @Override
    public List<Long> findItemSpecIdsByCharacterIds(List<Long> characterIds) {
        return jpaQueryFactory.selectFrom(item)
                .where(whereCharacterIds(characterIds))
                .fetch()
                .stream()
                .map(a-> a.getItemSpec().getItemSpecId())
                .collect(Collectors.toList());
    }

    public BooleanExpression whereItemIds(List<Long> itemIds) { return itemIds==null||itemIds.isEmpty()?null:item.itemId.in(itemIds); }

    public BooleanExpression whereCharacterIds(List<Long> characterIds) { return characterIds==null||characterIds.isEmpty()?null:item.itemId.in(characterIds); }

    public BooleanExpression whereItemId(Long itemId){ return itemId==null?null:item.itemId.eq(itemId); }

    public BooleanExpression whereActYn(Boolean actYn){ return actYn==null?null:item.actYn.eq(actYn); }

}
