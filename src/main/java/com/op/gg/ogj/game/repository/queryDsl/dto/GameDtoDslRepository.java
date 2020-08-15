package com.op.gg.ogj.game.repository.queryDsl.dto;

import com.op.gg.ogj.game.model.dto.GameResponse;
import com.op.gg.ogj.game.model.dto.GameSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GameDtoDslRepository {

    Page<GameResponse> findPageGameByGameParam(GameSearch gameSearch, Pageable pageable);

    GameResponse findGameByGameId(GameSearch gameSearch);

}
