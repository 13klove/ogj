package com.op.gg.ogj.game.repository.queryDsl.entity;

import com.op.gg.ogj.game.model.entity.Game;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;

import java.util.List;
import java.util.stream.Collectors;

import static com.op.gg.ogj.game.model.entity.QGame.game;
import static com.op.gg.ogj.gameInfo.model.entity.QGameInfo.gameInfo;

public class GameDslRepositoryImpl implements GameDslRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public GameDslRepositoryImpl(EntityManager em){
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Game findGameByGameId(Long id) {
        return jpaQueryFactory.select(game)
                .from(game)
                .join(game.gameInfo, gameInfo).fetchJoin()
                .where(game.gameId.eq(id))
                .fetchOne();
    }

    @Override
    public Game findGameGameInfoByGameId(Long id) {
        return jpaQueryFactory.select(game)
                .from(game)
                .join(game.gameInfo, gameInfo).fetchJoin()
                .where(game.gameId.eq(id))
                .fetchOne();
    }

    @Override
    public void updateGameRelActYn(List<Long> gameIds) {
        jpaQueryFactory.update(game)
                .set(game.actYn, false)
                .where(whereInGameIds(gameIds))
                .execute();
    }

    @Override
    public List<Long> findGameInfoIdsByGameIds(List<Long> gameIds) {
        return jpaQueryFactory.selectFrom(game)
                .join(game.gameInfo, gameInfo).fetchJoin()
                .where(whereInGameIds(gameIds))
                .fetch()
                .stream()
                .map(a->a.getGameInfo().getGameInfoId())
                .collect(Collectors.toList());
    }

    public BooleanExpression whereInGameIds(List<Long> gameIds){
        return gameIds==null||gameIds.isEmpty()?null:game.gameId.in(gameIds);
    }

}
