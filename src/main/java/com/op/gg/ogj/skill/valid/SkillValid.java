package com.op.gg.ogj.skill.valid;

import com.op.gg.ogj.config.exception.domain.ParamValidException;
import com.op.gg.ogj.skill.model.SkillType;
import com.op.gg.ogj.skill.model.entity.Skill;
import org.springframework.util.StringUtils;

import java.util.List;

public enum SkillValid {

    SKILL_SKILL_ID_LOCK("스킬ID가 없습니다."){
        public void validLogic(Long value){ if(value==null) throw new ParamValidException("스킬ID가 없습니다."); }
    },
    SKILL_SKILL_IDS_LOCK("스킬ID가 없습니다."){
        public void validLogic(List<Long> value){ if(value==null||value.isEmpty()) throw new ParamValidException("스킬ID가 없습니다."); }
    },
    SKILL_NAME_LOCK("스킬 이름은 필수 입니다."){
        public void validLogic(String value){ if(!StringUtils.hasText(value)) throw new ParamValidException("스킬 이름은 필수 입니다."); }
    },
    SKILL_USE_ENERGY_LOCK("스킬 사용 에너지는 필수 입니다."){
        public void validLogic(Integer value){ if(value==null) throw new ParamValidException("스킬 사용 에너지는 필수 입니다."); }
    },
    SKILL_USE_LIFE_LOCK("스킬 사용 생명력은 필수 입니다."){
        public void validLogic(Integer value){ if(value==null) throw new ParamValidException("스킬 사용 생명력은 필수 입니다."); }
    },
    SKILL_DAMAGE_LOCK("스킬 데미지는 필수 입니다."){
        public void validLogic(Integer value){ if(value==null) throw new ParamValidException("스킬 데미지는 필수 입니다."); }
    },
    SKILL_SKILL_TYPE_LOCK("스킬 타입은 필수 입니다."){
        public void validLogic(SkillType value){ if(value==null) throw new ParamValidException("스킬 타입은 필수 입니다."); }
    },
    SKILL_ULIMATE_YN("스킬의 궁극기 여부는 필수 입니다."){
        public void validLogic(Boolean value) { if(value==null) throw new ParamValidException("스킬의 궁극기 여부는 필수 입니다."); }
    },
    SKILL_EXIT_SKILL("이미 존재하는 스킬 입니다."){
        public void validLogic(Skill skill) { if(skill!=null) throw new ParamValidException("이미 존재하는 스킬 입니다."); }
    };

    private String desc;

    SkillValid(String desc){
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void validLogic(Long value) { }

    public void validLogic(List<Long> value) { }

    public void validLogic(Integer value) { }

    public void validLogic(String value) { }

    public void validLogic(Boolean value) { }

    public void validLogic(SkillType value) { }

    public void validLogic(Skill skill) { }

}
