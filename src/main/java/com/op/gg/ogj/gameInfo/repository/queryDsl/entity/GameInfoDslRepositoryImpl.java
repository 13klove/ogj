package com.op.gg.ogj.gameInfo.repository.queryDsl.entity;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static com.op.gg.ogj.gameInfo.model.entity.QGameInfo.gameInfo;

public class GameInfoDslRepositoryImpl implements GameInfoDslRepository{

    private JPAQueryFactory jpaQueryFactory;

    public GameInfoDslRepositoryImpl(EntityManager entityManager){
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public void updateGameInfoActYn(List<Long> gameInfoIds) {
        jpaQueryFactory.update(gameInfo)
                .set(gameInfo.actYn, false)
                .where(whereGameInfoId(gameInfoIds))
                .execute();
    }

    public BooleanExpression whereGameInfoId(List<Long> gameInfoIds){
        return gameInfoIds==null||gameInfoIds.isEmpty()?null:gameInfo.gameInfoId.in(gameInfoIds);
    }

}
