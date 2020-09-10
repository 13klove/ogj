package com.op.gg.ogj.itemSpec.valid;

import com.op.gg.ogj.config.exception.domain.ParamValidException;
import org.springframework.util.StringUtils;

public enum ItemSpecValid {

    ITEM_SPEC_IS_INFO_LOCK("아이템 설명이 없습니다."){
        public void validLogic(String value) {
            if(!StringUtils.hasText(value)) throw new ParamValidException("아이템 설명이 없습니다.");
        }
    },
    ITEM_SPEC_DAMAGE_RATE_LOCK("공격 효과가 없습니다."){
        public void validLogic(Integer value){
            if(value==null) throw new ParamValidException("공격 효과가 없습니다.");
        }
    },
    ITEM_SPEC_DEFENSE_RATE_LOCK("방어 효과가 없습니다."){
        public void validLogic(Integer value){
            if(value==null) throw new ParamValidException("방어 효과가 없습니다.");
        }
    };

    private String desc;

    ItemSpecValid(String desc){
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void validLogic(Integer value){}

    public void validLogic(String value){}

}
