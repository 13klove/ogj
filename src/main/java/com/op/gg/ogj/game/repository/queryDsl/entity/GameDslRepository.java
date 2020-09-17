package com.op.gg.ogj.game.repository.queryDsl.entity;

import com.op.gg.ogj.game.model.entity.Game;

import java.util.List;

public interface GameDslRepository {

    Game findGameByGameId(Long id);

    Game findGameGameInfoByGameId(Long id);

    void updateGameRelActYn(List<Long> gameIds);

    List<Long> findGameInfoIdsByGameIds(List<Long> gameIds);

}
