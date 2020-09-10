package com.op.gg.ogj.skin.valid;

import com.op.gg.ogj.config.exception.domain.ParamValidException;
import com.op.gg.ogj.skin.model.entity.Skin;
import org.springframework.util.StringUtils;

import java.util.List;

public enum SkinValid {

    SKIN_SKIN_ID_LOCK("스킨ID가 없습니다."){
        public void validLogic(Long value) { if(value==null) throw new ParamValidException("스킨ID가 없습니다."); }
    },
    SKIN_SKINS_ID_LOCK("스킨ID 없습니다."){
        public void validLogic(List<Long> value) { if(value==null||value.isEmpty()) throw new ParamValidException("스킨ID가 없습니다."); }
    },
    SKIN_SKIN_NM_LOCK("스킨 이름이 없습니다."){
        public void validLogic(String value){ if(!StringUtils.hasText(value)) throw new ParamValidException("스킨 이름이 없습니다."); }
    },
    SKIN_PRICE_LOCK("스킨 가격이 없습니다."){
        public void validLogic(Long value) { if(value==null) throw new ParamValidException("스킨 가격이 없습니다."); }
    },
    SKIN_EXIT_SKIN("이미 존재하는 스킨 입니다."){
        public void validLogic(Skin value) { if(value!=null) throw new ParamValidException(SkinValid.SKIN_EXIT_SKIN.getDesc()); }
    };

    private String desc;

    SkinValid(String desc){
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void validLogic(Long value) { }

    public void validLogic(List<Long> value) { }

    public void validLogic(String value) { }

    public void validLogic(Skin value) { }

}
