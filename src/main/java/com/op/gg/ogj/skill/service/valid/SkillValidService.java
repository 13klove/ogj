package com.op.gg.ogj.skill.service.valid;

import com.op.gg.ogj.character.model.entity.Character;
import com.op.gg.ogj.character.repository.CharacterJpaRepository;
import com.op.gg.ogj.character.valid.CharacterValid;
import com.op.gg.ogj.skill.model.dto.SkillParam;
import com.op.gg.ogj.skill.model.dto.SkillSearch;
import com.op.gg.ogj.skill.model.entity.Skill;
import com.op.gg.ogj.skill.repository.SkillJpaRepository;
import com.op.gg.ogj.skill.valid.SkillValid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SkillValidService {

    private final SkillJpaRepository skillJpaRepository;
    private final CharacterJpaRepository characterJpaRepository;

    public void createSkillValid(SkillParam skillParam) {
        createUpdateValid(skillParam);
        Skill skill = skillJpaRepository.findSkillByCharacter_CharacterIdAndSkillNm(skillParam.getCharacterId(), skillParam.getSkillNm());
        SkillValid.SKILL_EXIT_SKILL.validLogic(skill);
    }

    public void updateSkillValid(SkillParam skillParam) {
        SkillValid.SKILL_SKILL_ID_LOCK.validLogic(skillParam.getSkillId());
        createUpdateValid(skillParam);
        Skill skill = skillJpaRepository.findSkillByCharacter_CharacterIdAndSkillNmAndSkillIdIsNot(skillParam.getCharacterId(), skillParam.getSkillNm(), skillParam.getSkillId());
        SkillValid.SKILL_EXIT_SKILL.validLogic(skill);
    }

    public void listSkillValid(SkillSearch skillSearch) { CharacterValid.CHARACTER_ID_LOCK.validLogic(skillSearch.getCharacterId()); }

    public void detailSkillValid(SkillSearch skillSearch) { SkillValid.SKILL_SKILL_ID_LOCK.validLogic(skillSearch.getSkillId()); }

    public void delSkillValid(SkillParam skillParam) { SkillValid.SKILL_SKILL_ID_LOCK.validLogic(skillParam.getSkillId()); }

    public void delSkillsValid(SkillParam skillParam) { SkillValid.SKILL_SKILL_IDS_LOCK.validLogic(skillParam.getSkillsId()); }

    private void createUpdateValid(SkillParam skillParam) {
        CharacterValid.CHARACTER_ID_LOCK.validLogic(skillParam.getCharacterId());
        SkillValid.SKILL_NAME_LOCK.validLogic(skillParam.getSkillNm());
        SkillValid.SKILL_USE_ENERGY_LOCK.validLogic(skillParam.getUseEnergy());
        SkillValid.SKILL_USE_LIFE_LOCK.validLogic(skillParam.getUseLife());
        SkillValid.SKILL_DAMAGE_LOCK.validLogic(skillParam.getDamage());
        SkillValid.SKILL_SKILL_TYPE_LOCK.validLogic(skillParam.getSkillType());
        SkillValid.SKILL_ULIMATE_YN.validLogic(skillParam.getUlimateYn());

        Optional<Character> optionalCharacter = characterJpaRepository.findById(skillParam.getCharacterId());
        CharacterValid.CHARACTER_NO_HAVE.validLogic(optionalCharacter);
    }
}
