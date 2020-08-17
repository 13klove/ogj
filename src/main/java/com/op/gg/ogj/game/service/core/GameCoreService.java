package com.op.gg.ogj.game.service.core;

import com.op.gg.ogj.game.model.dto.GameParam;
import com.op.gg.ogj.game.model.dto.GameResponse;
import com.op.gg.ogj.game.model.dto.GameSearch;
import com.op.gg.ogj.game.model.entity.Game;
import com.op.gg.ogj.game.repository.GameJpaRepository;
import com.op.gg.ogj.gameInfo.model.entity.GameInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GameCoreService {

    private final GameJpaRepository gameJpaRepository;

    @Transactional
    public Long createGame(GameParam gameParam){
        Game game = gameParam.getGameType().create(gameParam);
        GameInfo gameInfo = GameInfo.createGameInfo(gameParam.getGameInfo1(), gameParam.getGameInfo2());
        game.gameInfoChange(gameInfo);
        gameJpaRepository.save(game);

        return game.getGameId();
    }

    @Transactional
    public Long updateGame(GameParam gameParam){
        Game game = gameJpaRepository.findGameGameInfoByGameId(gameParam.getGameId());
        game.gameUpdate(gameParam.getGameNm(), gameParam.getPrice(), gameParam.getBrand(), gameParam.getDeviceType());
        game.getGameInfo().gameInfoUpdate(gameParam.getGameInfo1(), gameParam.getGameInfo2());

        return game.getGameId();
    }

   public Page<GameResponse> pageGame(GameSearch gameSearch, Pageable pageable){
        return gameJpaRepository.findPageGameByGameParam(gameSearch, pageable);
    }

    public GameResponse detailGame(GameSearch gameSearch){
        return gameJpaRepository.findGameByGameId(gameSearch);
    }

}
