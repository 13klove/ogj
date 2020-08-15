package com.op.gg.ogj.game.repository.queryDsl.dto;

import com.op.gg.ogj.game.model.dto.GameResponse;
import com.op.gg.ogj.game.model.dto.GameSearch;
import org.springframework.data.domain.Page;

public interface GameDtoDslRepository {

    Page<GameResponse> findPageGameByGameParam(GameSearch gameSearch);

    GameResponse findGameByGameId(GameSearch gameSearch);

}
