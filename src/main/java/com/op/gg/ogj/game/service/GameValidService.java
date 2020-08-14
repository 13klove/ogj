package com.op.gg.ogj.game.service;

import com.op.gg.ogj.config.exception.domain.ParamValidException;
import com.op.gg.ogj.game.model.Game;
import com.op.gg.ogj.game.model.GameParam;
import com.op.gg.ogj.game.repository.GameJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class GameValidService {

    @Autowired
    private GameJpaRepository gameJpaRepository;

    public void createGameValid(GameParam gameParam){
        if(!StringUtils.hasText(gameParam.getGameInfo1())) throw new ParamValidException("게임 정보는 필수로 1개 이상 입력해야 합니다.");
        if(!StringUtils.hasText(gameParam.getGameNm())) throw new ParamValidException("게임 이름은 필수 입니다.");
        if(gameParam.getGameType() == null) throw new ParamValidException("게임타입은 필수 입니다.");
        if(gameParam.getPrice() == null) throw new ParamValidException("게임 가격은 필수 입니다.");
        if(!StringUtils.hasText(gameParam.getBrand())) throw new ParamValidException("게임 브렌드는 필수 입니다.");
        if(gameParam.getDeviceType() == null) throw new ParamValidException("디바이스 타입은 필수 입니다.");

        Optional<Game> game = gameJpaRepository.findGameByGameNm(gameParam.getGameNm());
        if(game.isPresent()) throw new ParamValidException("이미 등록되어 있는 게임 입니다.");
    }

    public void updateGameValid(GameParam gameParam){
        if(gameParam.getGameId() == null) throw new ParamValidException("요청한 게임정보에 이상이 있습니다.");
        if(!StringUtils.hasText(gameParam.getGameInfo1())) throw new ParamValidException("게임 정보는 필수로 1개 이상 입력해야 합니다.");
        if(!StringUtils.hasText(gameParam.getGameNm())) throw new ParamValidException("게임 이름은 필수 입니다.");
        if(gameParam.getGameType() == null) throw new ParamValidException("게임타입은 필수 입니다.");
        if(gameParam.getPrice() == null) throw new ParamValidException("게임 가격은 필수 입니다.");
        if(!StringUtils.hasText(gameParam.getBrand())) throw new ParamValidException("게임 브렌드는 필수 입니다.");
        if(gameParam.getDeviceType() == null) throw new ParamValidException("디바이스 타입은 필수 입니다.");

        Optional<Game> game = gameJpaRepository.findGameByGameNmAndGameIdIsNot(gameParam.getGameId(), gameParam.getGameNm());
        if(game.isPresent()) throw new ParamValidException("이미 등록되어 있는 게임 입니다.");
    }

}
