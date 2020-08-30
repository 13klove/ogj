package com.op.gg.ogj.map.repository.queryDsl.entity;

import com.op.gg.ogj.map.model.entity.Map;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;

import static com.op.gg.ogj.map.model.entity.QMap.map;

public class MapDslRepositoryImpl implements MapDslRepository{

    private final JPAQueryFactory jpaQueryFactory;

    public MapDslRepositoryImpl(EntityManager em){
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Map findMapByGameIdMapId(Long gameId, Long mapId) {
        return jpaQueryFactory.selectFrom(map)
                .where(whereGameId(gameId), whereMapId(mapId))
                .fetchOne();
    }

    private BooleanExpression whereGameId(Long gameId){
        return gameId==null?null:map.game.gameId.eq(gameId);
    }

    private BooleanExpression whereMapId(Long mapId){
        return mapId==null?null:map.mapId.eq(mapId);
    }

}
