package com.op.gg.ogj.skill.service.core;

import com.op.gg.ogj.character.model.entity.Character;
import com.op.gg.ogj.character.repository.CharacterJpaRepository;
import com.op.gg.ogj.skill.model.dto.SkillParam;
import com.op.gg.ogj.skill.model.dto.SkillResponse;
import com.op.gg.ogj.skill.model.dto.SkillSearch;
import com.op.gg.ogj.skill.model.entity.Skill;
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
        Character character = characterJpaRepository.findById(skillParam.getCharacterId()).get();
        Skill skill = Skill.createSkill(skillParam.getSkillNm(), skillParam.getUseEnergy(), skillParam.getUseLife(), skillParam.getDamage(), skillParam.getSkillType(), skillParam.getUlimateYn(), skillParam.getImgUrl());
        skill.smCharacterChange(character);
        skillJpaRepository.save(skill);
        return skill.getSkillId();
    }

    @Transactional
    public Long updateSkill(SkillParam skillParam) {
        Skill skill = skillJpaRepository.findSkillAndCharacterBySkillId(skillParam.getSkillId());
        skill.updateSkill(skillParam.getSkillNm(), skillParam.getUseEnergy(), skillParam.getUseLife(), skillParam.getDamage(), skillParam.getSkillType(), skillParam.getUlimateYn(), skillParam.getImgUrl());
        return skill.getSkillId();
    }

    public List<SkillResponse> listSkill(SkillSearch skillSearch) {
        return skillJpaRepository.pageSkill(skillSearch);
    }

    public SkillResponse detailSkill(SkillSearch skillSearch) {
        return skillJpaRepository.findSkillBySkillId(skillSearch.getSkillId());
    }

    @Transactional
    public void delSkill(SkillParam skillParam) {
        Skill skill = skillJpaRepository.findSkillAndCharacterBySkillId(skillParam.getSkillId());
        skill.delSkill();
    }

    @Transactional
    public void delSkillsValid(SkillParam skillParam) {
        List<Skill> skills = skillJpaRepository.findSkillsBySkillsId(skillParam.getSkillsId());
        skills.forEach(a->{a.delSkill();});
    }
}
