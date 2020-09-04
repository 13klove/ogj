package com.op.gg.ogj.character.valid;

import com.op.gg.ogj.character.model.dto.CharacterParam;
import com.op.gg.ogj.config.exception.domain.ParamValidException;
import org.springframework.util.StringUtils;


public enum CharacterValid {

    CHARACTER_ID_LOCK("케릭터ID가 없습니다."){
        public void validLogic(CharacterParam characterParam) {
            if(characterParam.getCharacterId()==null) throw new ParamValidException("케릭터ID가 없습니다.");
        }
    },
    CHARACTER_IDS_LOCK("케릭터ID가 없습니다."){
        public void validLogic(CharacterParam characterParam) {
            if(characterParam.getCharactersId() == null || characterParam.getCharactersId().isEmpty()) throw new ParamValidException("케릭터ID가 없습니다.");
        }
    },
    CHARACTER_NAME_EXIST("이미 존재하는 케릭터 입니다."){
        public void validLogic(CharacterParam characterParam) { }
    },
    CHARACTER_NAME_LOCK("케릭터 이름은 필수 입니다."){
        public void validLogic(CharacterParam characterParam){
            if(!StringUtils.hasText(characterParam.getCharacterNm())) throw new ParamValidException("케릭터 이름은 필수 입니다.");
        }
    },
    CHARACTER_TYPE_LOCK("케릭터 타입은 필수 입니다."){
        public void validLogic(CharacterParam characterParam){
            if(characterParam.getCharacterType()==null) throw new ParamValidException("케릭터 타입은 필수 입니다.");
        }
    },
    CHARACTER_LIFE_LOCK("케릭터 생명력은 필수 입니다."){
        public void validLogic(CharacterParam characterParam){
            if(characterParam.getLife()==null) throw new ParamValidException("케릭터 생명력은 필수 입니다.");
        }
    },
    CHARACTER_ENERGY_LOCK("케릭터 에어지는 필수 입니다."){
        public void validLogic(CharacterParam characterParam){
            if(characterParam.getEnergy()==null) throw new ParamValidException("케릭터 에어지는 필수 입니다.");
        }
    },
    CHARACTER_NO_HAVE("케릭터가 존재하지 않습니다."){
        public void validLogic(CharacterParam characterParam){ }
    };

    private String desc;
    abstract public void validLogic(CharacterParam characterParam);

    CharacterValid(String desc){
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

}
