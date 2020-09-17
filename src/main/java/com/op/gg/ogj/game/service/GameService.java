package com.op.gg.ogj.game.service;

import com.op.gg.ogj.game.model.dto.GameParam;
import com.op.gg.ogj.game.model.dto.GameResponse;
import com.op.gg.ogj.game.model.dto.GameSearch;
import com.op.gg.ogj.game.service.core.GameCoreService;
import com.op.gg.ogj.game.service.valid.GameValidService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GameService {

    private final GameValidService gameValidService;
    private final GameCoreService gameCoreService;

    @Transactional
    public Long createGame(GameParam gameParam){
        gameValidService.createGameValid(gameParam);
        return gameCoreService.createGame(gameParam);
    }

    @Transactional
    public Long updateGame(GameParam gameParam){
        gameValidService.updateGameValid(gameParam);
        return gameCoreService.updateGame(gameParam);
    }

    public Page<GameResponse> pageGame(GameSearch gameSearch, Pageable pageable){
        gameValidService.pageGameValid(gameSearch);
        return gameCoreService.pageGame(gameSearch, pageable);
    }

    public GameResponse detailGame(GameSearch gameSearch){
        gameValidService.detailGame(gameSearch);
        return gameCoreService.detailGame(gameSearch);
    }

    @Transactional
    public void delGame(GameParam gameParam) {
        gameValidService.delGame(gameParam.getGameId());
        gameCoreService.delGame(gameParam);
    }

    @Transactional
    public void delGames(GameParam gameParam) {
        gameValidService.delGames(gameParam.getGameIds());
        gameCoreService.delGames(gameParam);
    }
}
