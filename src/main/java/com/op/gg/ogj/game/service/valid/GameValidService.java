package com.op.gg.ogj.game.service.valid;

import com.op.gg.ogj.config.exception.domain.ParamValidException;
import com.op.gg.ogj.game.model.dto.GameParam;
import com.op.gg.ogj.game.model.dto.GameSearch;
import com.op.gg.ogj.game.model.entity.Game;
import com.op.gg.ogj.game.repository.GameJpaRepository;
import com.op.gg.ogj.game.valied.GameValid;
import com.op.gg.ogj.gameInfo.valid.GameInfoValid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GameValidService {

    private final GameJpaRepository gameJpaRepository;

    public void createGameValid(GameParam gameParam){
        createUpdate(gameParam);
        Optional<Game> game = gameJpaRepository.findGameByGameNm(gameParam.getGameNm());
        GameValid.GAME_EXIT.validLogic(game);
    }

    public void updateGameValid(GameParam gameParam){
        GameValid.GAME_ID_LOCK.validLogic(gameParam.getGameId());
        createUpdate(gameParam);
        Optional<Game> game = gameJpaRepository.findGameByGameNmAndGameIdIsNot(gameParam.getGameNm(), gameParam.getGameId());
        if(game.isPresent()) throw new ParamValidException("이미 등록되어 있는 게임 입니다.");
    }

    public void pageGameValid(GameSearch gameSearch){
        GameValid.GAME_REG_DATE_RANGE.validLogic(gameSearch.getStartDate(), gameSearch.getEndDate());
        GameValid.GAME_PRICE_RANGE.validLogic(gameSearch.getStartPrice(), gameSearch.getEndPrice());
    }

    public void detailGame(GameSearch gameSearch){ GameValid.GAME_ID_LOCK.validLogic(gameSearch.getGameId()); }

    public void delGame(Long gameId){
        GameValid.GAME_ID_LOCK.validLogic(gameId);
        Optional<Game> game = gameJpaRepository.findById(gameId);
        GameValid.GAME_NO_HAVE.validLogic(game);
    }

    public void delGames(List<Long> gamesId){ GameValid.GAME_IDS_LOCK.validLogic(gamesId); }

    private void createUpdate(GameParam gameParam) {
        GameInfoValid.GAME_INFO_GAME_INFO1_LOCK.validLogic(gameParam.getGameInfo1());
        GameValid.GAME_GAME_NM_LOCK.validLogic(gameParam.getGameNm());
        GameValid.GAME_GAME_TYPE_LOCK.validLogic(gameParam.getGameType());
        GameValid.GAME_PRICE_LOCK.validLogic(gameParam.getPrice());
        GameValid.GAME_GAME_BRAND_LOCK.validLogic(gameParam.getBrand());
        GameValid.GAME_DEVICE_TYPE_LOCK.validLogic(gameParam.getDeviceType());
    }

}
