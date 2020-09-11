package com.op.gg.ogj.item.repository.queryDsl.dto;

import com.op.gg.ogj.item.model.ItemType;
import com.op.gg.ogj.item.model.dto.ItemResponse;
import com.op.gg.ogj.item.model.dto.QItemResponse;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;

import static com.op.gg.ogj.character.model.entity.QCharacter.character;
import static com.op.gg.ogj.item.model.entity.QItem.item;
import static com.op.gg.ogj.itemSpec.model.QItemSpec.itemSpec;

public class ItemDtoDslRepositoryImpl implements ItemDtoDslRepository{

    private final JPAQueryFactory jpaQueryFactory;

    public ItemDtoDslRepositoryImpl(EntityManager entityManager){
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    }


    @Override
    public Page<ItemResponse> pageItem(Long characterId, String itemNm, ItemType itemType, Pageable pageable) {
        QueryResults<ItemResponse> itemResponseQueryResults = jpaQueryFactory.select(new QItemResponse(item.itemId, item.itemNm, item.itemType, item.itemSpec.isInfo, item.itemSpec.damageRate, item.itemSpec.defenseRate))
                .from(item)
                .join(item.character, character)
                .join(item.itemSpec, itemSpec)
                .where(whereCharacterId(characterId), whereItemNm(itemNm), whereItemType(itemType), whereActYn(true))
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetchResults();
        return new PageImpl(itemResponseQueryResults.getResults(), pageable, itemResponseQueryResults.getTotal());
    }

    @Override
    public ItemResponse detailItem(Long characterId, Long itemId) {
        return jpaQueryFactory.select(new QItemResponse(item.itemId, item.itemNm, item.itemType, item.itemSpec.isInfo, item.itemSpec.damageRate, item.itemSpec.defenseRate))
                .from(item)
                .join(item.character, character)
                .where(whereCharacterId(characterId), whereItemId(itemId), whereActYn(true))
                .fetchOne();
    }

    private BooleanExpression whereItemId(Long itemId) {
        return itemId==null?null:item.itemId.eq(itemId);
    }

    public BooleanExpression whereCharacterId(Long characterId){
        return characterId==null?null:item.character.characterId.eq(characterId);
    }

    public BooleanExpression whereItemNm(String itemNm){
        return itemNm==null?null:item.itemNm.eq(itemNm);
    }

    public BooleanExpression whereItemType(ItemType itemType){
        return itemType==null?null:item.itemType.eq(itemType);
    }

    public BooleanExpression whereActYn(Boolean actYn){
        return actYn==null?null:item.actYn.eq(actYn);
    }

}
