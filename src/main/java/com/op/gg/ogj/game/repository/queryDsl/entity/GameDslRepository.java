package com.op.gg.ogj.game.repository.queryDsl.entity;

import com.op.gg.ogj.game.model.entity.Game;

public interface GameDslRepository {

    Game findGameByGameId(Long id);

    Game findGameGameInfoByGameId(Long id);

}
