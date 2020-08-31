package com.op.gg.ogj.skill.service.core;

import com.op.gg.ogj.character.repository.CharacterJpaRepository;
import com.op.gg.ogj.skill.model.dto.SkillParam;
import com.op.gg.ogj.skill.model.dto.SkillResponse;
import com.op.gg.ogj.skill.model.dto.SkillSearch;
import com.op.gg.ogj.skill.repository.SkillJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SkillCoreService {

    private final SkillJpaRepository skillJpaRepository;
    private final CharacterJpaRepository characterJpaRepository;

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

    public SkillResponse detailSkill(SkillSearch skillSearch) {
        return null;
    }

    public void delSkill(SkillParam skillParam) {
    }

    public void delSkillsValid(SkillParam skillParam) {
    }
}
