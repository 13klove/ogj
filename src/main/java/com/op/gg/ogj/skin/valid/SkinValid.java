package com.op.gg.ogj.skin.valid;

import com.op.gg.ogj.config.valid.CommonValid;
import com.op.gg.ogj.skin.model.dto.SkinParam;

public enum SkinValid implements CommonValid<SkinParam> {

    SKIN_SKIN_ID_LOCK("스킨ID가 없습니다."){
        public void validLogic(SkinParam param) {

        }
    };

    private String desc;

    SkinValid(String desc){
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

}
