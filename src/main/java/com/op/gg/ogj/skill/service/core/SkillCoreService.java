package com.op.gg.ogj.skill.service.core;

import com.op.gg.ogj.skill.model.dto.SkillParam;
import com.op.gg.ogj.skill.model.dto.SkillResponse;
import com.op.gg.ogj.skill.model.dto.SkillSearch;
import com.op.gg.ogj.skill.repository.SkillJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SkillCoreService {

    private final SkillJpaRepository skillJpaRepository;

    @Transactional
    public Long createSkill(SkillParam skillParam) {
        return null;
    }

    @Transactional
    public Long updateSkill(SkillParam skillParam) {
        return null;
    }

    public List<SkillResponse> listSkill(SkillSearch skillSearch) {
        return null;
    }
}
