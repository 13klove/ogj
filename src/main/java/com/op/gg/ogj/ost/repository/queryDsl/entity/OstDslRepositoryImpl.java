package com.op.gg.ogj.ost.repository.queryDsl.entity;

import com.op.gg.ogj.ost.model.dto.OstParam;
import com.op.gg.ogj.ost.model.entity.Ost;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;

import static com.op.gg.ogj.game.model.entity.QGame.game;
import static com.op.gg.ogj.map.model.entity.QMap.map;
import static com.op.gg.ogj.ost.model.entity.QOst.ost;

public class OstDslRepositoryImpl implements OstDslRepository{

    private final JPAQueryFactory jpaQueryFactory;

    public OstDslRepositoryImpl(EntityManager em){
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Ost findOstGameMapByOstId(Long ostId) {
        return jpaQueryFactory.selectFrom(ost)
                .join(ost.game, game)
                .leftJoin(ost.map, map)
                .where(whereOstId(ostId)).fetchOne();
    }

    @Override
    public Ost findOstInMapByOstId(OstParam ostParam) {
        return jpaQueryFactory.select(ost)
                .leftJoin(ost.map, map)
                .where(whereOstId(ostParam.getOstId()))
                .fetchOne();
    }

    public BooleanExpression whereOstId(Long ostId){
        return ostId == null?null:ost.ostId.eq(ostId);
    }
}
