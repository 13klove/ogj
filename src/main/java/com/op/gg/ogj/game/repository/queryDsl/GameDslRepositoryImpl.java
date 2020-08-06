package com.op.gg.ogj.game.repository.queryDsl;

import com.op.gg.ogj.game.model.Game;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;

import static com.op.gg.ogj.game.model.QGame.game;
import static com.op.gg.ogj.gameInfo.model.QGameInfo.gameInfo;

public class GameDslRepositoryImpl implements GameDslRepository{

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
}
