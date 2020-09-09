package com.op.gg.ogj.character.valid;

import com.op.gg.ogj.character.model.CharacterType;
import com.op.gg.ogj.character.model.entity.Character;
import com.op.gg.ogj.config.exception.domain.ParamValidException;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;


public enum CharacterValid {

    CHARACTER_ID_LOCK("케릭터ID가 없습니다."){
        public void validLogic(Long value) {
            if(value==null) throw new ParamValidException("케릭터ID가 없습니다.");
        }
    },
    CHARACTER_IDS_LOCK("케릭터ID가 없습니다."){
        public void validLogic(List<Long> values) {
            if(values == null || values.isEmpty()) throw new ParamValidException("케릭터ID가 없습니다.");
        }
    },
    CHARACTER_NAME_EXIST("이미 존재하는 케릭터 입니다."){
        public void validLogic(Object value) {
            if(value!=null) throw new ParamValidException("이미 존재하는 캐릭터 입니다.");
        }
    },
    CHARACTER_NAME_LOCK("케릭터 이름은 필수 입니다."){
        public void validLogic(String value){
            if(!StringUtils.hasText(value)) throw new ParamValidException("케릭터 이름은 필수 입니다.");
        }
    },
    CHARACTER_TYPE_LOCK("케릭터 타입은 필수 입니다."){
        public void validLogic(CharacterType value){
            if(value==null) throw new ParamValidException("케릭터 타입은 필수 입니다.");
        }
    },
    CHARACTER_LIFE_LOCK("케릭터 생명력은 필수 입니다."){
        public void validLogic(Integer value){
            if(value==null) throw new ParamValidException("케릭터 생명력은 필수 입니다.");
        }
    },
    CHARACTER_ENERGY_LOCK("케릭터 에어지는 필수 입니다."){
        public void validLogic(Integer value){
            if(value==null) throw new ParamValidException("케릭터 에어지는 필수 입니다.");
        }
    },
    CHARACTER_NO_HAVE("케릭터가 존재하지 않습니다."){
        public void validLogic(Optional<Character> value){
            value.orElseThrow(()->new ParamValidException("케릭터가 존재하지 않습니다."));
        }
    };

    private String desc;

    CharacterValid(String desc){
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }


    public void validLogic(Object param) { }

    public void validLogic(Optional<Character> value) { }

    public void validLogic(Long value) { }

    public void validLogic(List<Long> value) { }

    public void validLogic(Integer value) { }

    public void validLogic(String value) { }

    public void validLogic(CharacterType value) { }

}
