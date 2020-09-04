package com.op.gg.ogj.skill.valid;

import com.op.gg.ogj.config.exception.domain.ParamValidException;
import com.op.gg.ogj.skill.model.dto.SkillParam;

public enum SkillValid {

    SKILL_SKILL_ID_LOCK("스킬ID가 없습니다."){
        public void validLogic(SkillParam skillParam){
            if(skillParam.getSkillId()==null) throw new ParamValidException("스킬ID가 없습니다.");
        }
    },
    SKILL_SKILL_IDS_LOCK("스킬ID가 없습니다."){
        public void validLogic(SkillParam skillParam){
            if(skillParam.getSkillsId()==null||skillParam.getSkillsId().isEmpty()) throw new ParamValidException("스킬ID가 없습니다.");
        }
    },
    SKILL_NAME_LOCK("스킬 이름은 필수 입니다."){
        public void validLogic(SkillParam skillParam){
            if(skillParam.getSkillNm()==null) throw new ParamValidException("스킬 이름은 필수 입니다.");
        }
    },
    SKILL_USE_ENERGY_LOCK("스킬 사용 에너지는 필수 입니다."){
        public void validLogic(SkillParam skillParam){
            if(skillParam.getUseEnergy()==null) throw new ParamValidException("스킬 사용 에너지는 필수 입니다.");
        }
    },
    SKILL_USE_LIFE_LOCK("스킬 사용 생명력은 필수 입니다."){
        public void validLogic(SkillParam skillParam){
            if(skillParam.getUseLife()==null) throw new ParamValidException("스킬 사용 생명력은 필수 입니다.");
        }
    },
    SKILL_DAMAGE_LOCK("스킬 데미지는 필수 입니다."){
        public void validLogic(SkillParam skillParam){
            if(skillParam.getDamage()==null) throw new ParamValidException("스킬 데미지는 필수 입니다.");
        }
    },
    SKILL_SKILL_TYPE_LOCK("스킬 타입은 필수 입니다."){
        public void validLogic(SkillParam skillParam){
            if(skillParam.getSkillType()==null) throw new ParamValidException("스킬 타입은 필수 입니다.");
        }
    },
    SKILL_ULIMATE_YN("스킬의 궁극기 여부는 필수 입니다."){
        public void validLogic(SkillParam skillParam) {
            if(skillParam.getUlimateYn()==null) throw new ParamValidException("스킬의 궁극기 여부는 필수 입니다.");
        }
    },
    SKILL_EXIT_SKILL("이미 존재하는 스킬 입니다."){
        public void validLogic(SkillParam skillParam) { }
    };

    private String desc;
    abstract public void validLogic(SkillParam skillParam);

    SkillValid(String desc){
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

}
