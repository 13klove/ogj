package com.op.gg.ogj.game.service;

import com.op.gg.ogj.config.exception.domain.ParamValidException;
import com.op.gg.ogj.game.model.Game;
import com.op.gg.ogj.game.model.GameParam;
import com.op.gg.ogj.game.repository.GameJpaRepository;
import com.op.gg.ogj.gameInfo.model.GameInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GameService {

    private final GameJpaRepository gameJpaRepository;

    @Transactional
    public Long createGame(GameParam gameParam){
        Game fps = gameParam.getGameType().create(gameParam);
        GameInfo gameInfo = GameInfo.createGameInfo(gameParam.getGameInfo1(), gameParam.getGameInfo2());
        fps.smGameInfoChange(gameInfo);
        gameJpaRepository.save(fps);
        return fps.getGameId();
    }

    public void gameValid(BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            List<ObjectError> list =  bindingResult.getAllErrors();
            for(ObjectError e : list) {
                throw new ParamValidException(e.getDefaultMessage());
            }
        }
    }

}
