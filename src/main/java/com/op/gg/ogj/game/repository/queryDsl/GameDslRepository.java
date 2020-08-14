package com.op.gg.ogj.game.repository.queryDsl;

import com.op.gg.ogj.game.model.Game;

public interface GameDslRepository {

    Game findGameByGameId(Long id);

    Game findGameGameInfoByGameId(Long id);

}
