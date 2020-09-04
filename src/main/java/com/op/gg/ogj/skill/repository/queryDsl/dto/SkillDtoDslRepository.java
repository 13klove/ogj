package com.op.gg.ogj.skill.repository.queryDsl.dto;

import com.op.gg.ogj.skill.model.dto.SkillResponse;
import com.op.gg.ogj.skill.model.dto.SkillSearch;

import java.util.List;

public interface SkillDtoDslRepository {

    List<SkillResponse> pageSkill(SkillSearch skillSearch);

    SkillResponse findSkillBySkillId(Long skillId);

}
