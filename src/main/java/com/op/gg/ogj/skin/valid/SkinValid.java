package com.op.gg.ogj.skin.valid;

import com.op.gg.ogj.config.exception.domain.ParamValidException;
import com.op.gg.ogj.config.valid.ObjectValid;
import com.op.gg.ogj.skin.model.dto.SkinParam;
import org.springframework.util.StringUtils;

public enum SkinValid implements ObjectValid<SkinParam> {

    SKIN_SKIN_ID_LOCK("스킨ID가 없습니다."){
        public void validLogic(SkinParam param) {
            if(param.getSkinId()==null) throw new ParamValidException("스킨ID가 없습니다.");
        }
    },
    SKIN_SKINS_ID_LOCK("스킨ID 없습니다."){
        public void validLogic(SkinParam param) {
            if(param.getSkinsId()==null||param.getSkinsId().isEmpty()) throw new ParamValidException("스킨ID가 없습니다.");
        }
    },
    SKIN_SKIN_NM_LOCK("스킨 이름이 없습니다."){
        public void validLogic(SkinParam param){
            if(!StringUtils.hasText(param.getSkinNm())) throw new ParamValidException("스킨 이름이 없습니다.");
        }
    },
    SKIN_PRICE_LOCK("스킨 가격이 없습니다."){
        public void validLogic(SkinParam param) {
            if(param.getPrice()==null) throw new ParamValidException("스킨 가격이 없습니다.");
        }
    },
    SKIN_EXIT_SKIN("이미 존재하는 스킨 입니다."){
        public void validLogic(SkinParam param) { }
    };

    private String desc;

    SkinValid(String desc){
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

}
