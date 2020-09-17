package com.op.gg.ogj.gameInfo.valid;

import com.op.gg.ogj.config.exception.domain.ParamValidException;
import org.springframework.util.StringUtils;

public enum GameInfoValid {

    GAME_INFO_GAME_INFO1_LOCK("등록하려는 맵은 없습니다."){
        public void validLogic(String value){ if(!StringUtils.hasText(value)) throw new ParamValidException("게임 정보1이 없습니다.."); }
    };

    private String desc;

    GameInfoValid(String desc){
        this.desc = desc;
    }

    public void validLogic(String value) { }

}
