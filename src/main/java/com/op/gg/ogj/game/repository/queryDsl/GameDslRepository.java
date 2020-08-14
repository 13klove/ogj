package com.op.gg.ogj.game.repository.queryDsl;

import com.op.gg.ogj.game.model.dto.GameResponse;
import com.op.gg.ogj.game.model.dto.GameSearch;
import com.op.gg.ogj.game.model.entity.Game;
import org.springframework.data.domain.Page;

public interface GameDslRepository {

    Game findGameByGameId(Long id);

    Game findGameGameInfoByGameId(Long id);

    Page<GameResponse> findPageGameByGameParam(GameSearch gameSearch);

}
