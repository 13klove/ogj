package com.op.gg.ogj.game.service;

import com.op.gg.ogj.game.model.Game;
import com.op.gg.ogj.game.model.GameParam;
import com.op.gg.ogj.game.repository.GameJpaRepository;
import com.op.gg.ogj.gameInfo.model.GameInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GameService {

    private final GameValidService gameValidService;
    private final GameJpaRepository gameJpaRepository;

    @Transactional
    public Long createGame(GameParam gameParam){
        gameValidService.createGameValid(gameParam);

        Game game = gameParam.getGameType().create(gameParam);
        GameInfo gameInfo = GameInfo.createGameInfo(gameParam.getGameInfo1(), gameParam.getGameInfo2());
        game.gameInfoChange(gameInfo);
        gameJpaRepository.save(game);

        return game.getGameId();
    }

    @Transactional
    public Long updateGame(GameParam gameParam){
        gameValidService.updateGameValid(gameParam);

        Game game = gameJpaRepository.findGameGameInfoByGameId(gameParam.getGameId());
        game.gameUpdate(gameParam.getGameNm(), gameParam.getPrice(), gameParam.getBrand(), gameParam.getDeviceType());
        game.getGameInfo().gameInfoUpdate(gameParam.getGameInfo1(), gameParam.getGameInfo2());

        return game.getGameId();
    }

}
