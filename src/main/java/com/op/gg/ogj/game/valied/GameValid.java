package com.op.gg.ogj.game.valied;

import com.op.gg.ogj.config.exception.domain.ParamValidException;
import com.op.gg.ogj.game.model.DeviceType;
import com.op.gg.ogj.game.model.GameType;
import com.op.gg.ogj.game.model.entity.Game;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public enum GameValid {

    GAME_PRICE_LOCK("게임 가격은 필수 입니다."){
        public void validLogic(Integer value) { if(value==null) throw new ParamValidException("게임 가격은 필수 입니다."); }
    },
    GAME_GAME_TYPE_LOCK("게임 타입이 없습니다."){
        public void validLogic(GameType value) { if(value==null) throw new ParamValidException("게임 타입이 없습니다."); }
    },
    GAME_GAME_NM_LOCK("게임 이름이 없습니다."){
        public void validLogic(String value) { if(!StringUtils.hasText(value)) throw new ParamValidException("게임 이름이 없습니다."); }
    },
    GAME_GAME_BRAND_LOCK("게임 브렌드는 필수 입니다."){
        public void validLogic(String value) { if(!StringUtils.hasText(value)) throw new ParamValidException("게임 브렌드는 필수 입니다."); }
    },
    GAME_ID_LOCK("게임ID가 없습니다."){
        public void validLogic(Long value) { if(value==null) throw new ParamValidException("게임ID가 없습니다."); }
    },
    GAME_IDS_LOCK("게임ID가 없습니다."){
        public void validLogic(List<Long> value) { if(value==null) throw new ParamValidException("게임ID가 없습니다."); }
    },
    GAME_DEVICE_TYPE_LOCK("디바이스 타입은 필수 입니다."){
        public void validLogic(DeviceType value) { if(value==null) throw new ParamValidException("디바이스 타입은 필수 입니다."); }
    },
    GAME_NO_HAVE("게임이 존재 하지 않습니다."){
        public void validLogic(Optional<Game> value) { value.orElseThrow(()->new ParamValidException("게임이 존재 하지 않습니다.")); }
    },
    GAME_EXIT("이미 등록되어 있는 게임 입니다."){
        public void validLogic(Optional<Game> value) { if(value.isPresent()) throw new ParamValidException("이미 등록되어 있는 게임 입니다."); }
    },
    GAME_REG_DATE_RANGE("등록일은 필수 값 입니다."){
        public void validLogic(LocalDate stValue, LocalDate enValue) { if(stValue==null||enValue==null) throw new ParamValidException("등록일은 필수 값 입니다."); }
    },
    GAME_PRICE_RANGE("등록일은 필수 값 입니다."){
        public void validLogic(Integer stValue, Integer enValue) {
            if((stValue!=null&&enValue==null)||(stValue==null&&enValue!=null)) throw new ParamValidException("가격은 시작가격과 종료가격 둘다 입력해야 합니다.");
        }
    };

    private String desc;

    GameValid(String desc){
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void validLogic(Long value) { }
    public void validLogic(List<Long> value) { }
    public void validLogic(Integer value) { }
    public void validLogic(String value) { }
    public void validLogic(DeviceType value) { }
    public void validLogic(GameType value) { }
    public void validLogic(Optional<Game> value) { }
    public void validLogic(LocalDate stValue, LocalDate enValue) { }
    public void validLogic(Integer stValue, Integer enValue) { }

}
