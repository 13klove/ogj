package com.op.gg.ogj.skill.repository.queryDsl.entity;

import com.op.gg.ogj.skill.model.entity.Skill;

import java.util.List;

public interface SkillDslRepository {

    Skill findSkillAndCharacterBySkillId(Long skillId);

    List<Skill> findSkillsBySkillsId(List<Long> skillsId);

}
