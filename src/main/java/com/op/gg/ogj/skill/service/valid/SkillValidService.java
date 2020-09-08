package com.op.gg.ogj.skill.service.valid;

import com.op.gg.ogj.character.model.dto.CharacterParam;
import com.op.gg.ogj.character.model.entity.Character;
import com.op.gg.ogj.character.repository.CharacterJpaRepository;
import com.op.gg.ogj.character.valid.CharacterValid;
import com.op.gg.ogj.config.exception.domain.ParamValidException;
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
        if(skill!=null) throw new ParamValidException(SkillValid.SKILL_EXIT_SKILL.getDesc());
    }

    public void updateSkillValid(SkillParam skillParam) {
        createUpdateValid(skillParam);

        SkillValid.SKILL_SKILL_ID_LOCK.validLogic(skillParam);

        Skill skill = skillJpaRepository.findSkillByCharacter_CharacterIdAndSkillNmAndSkillIdIsNot(skillParam.getCharacterId(), skillParam.getSkillNm(), skillParam.getSkillId());
        if(skill!=null) throw new ParamValidException(SkillValid.SKILL_EXIT_SKILL.getDesc());
    }

    public void listSkillValid(SkillSearch skillSearch) {
        CharacterParam characterParam = CharacterParam.builder().characterId(skillSearch.getCharacterId()).build();
        CharacterValid.CHARACTER_ID_LOCK.validLogic(characterParam);
    }

    public void detailSkillValid(SkillSearch skillSearch) {
        SkillParam skillParam = SkillParam.builder().skillId(skillSearch.getSkillId()).build();
        SkillValid.SKILL_SKILL_ID_LOCK.validLogic(skillParam);
    }

    public void delSkillValid(SkillParam skillParam) {
        SkillValid.SKILL_SKILL_ID_LOCK.validLogic(skillParam);
    }

    public void delSkillsValid(SkillParam skillParam) {
        SkillValid.SKILL_SKILL_IDS_LOCK.validLogic(skillParam);
    }

    private void createUpdateValid(SkillParam skillParam) {
        CharacterParam characterParam = CharacterParam.builder().characterId(skillParam.getCharacterId()).build();

        CharacterValid.CHARACTER_ID_LOCK.validLogic(characterParam);
        SkillValid.SKILL_NAME_LOCK.validLogic(skillParam);
        SkillValid.SKILL_USE_ENERGY_LOCK.validLogic(skillParam);
        SkillValid.SKILL_USE_LIFE_LOCK.validLogic(skillParam);
        SkillValid.SKILL_DAMAGE_LOCK.validLogic(skillParam);
        SkillValid.SKILL_SKILL_TYPE_LOCK.validLogic(skillParam);
        SkillValid.SKILL_ULIMATE_YN.validLogic(skillParam);

        Optional<Character> optionalCharacter = characterJpaRepository.findById(skillParam.getCharacterId());
        optionalCharacter.orElseThrow(() -> new ParamValidException(CharacterValid.CHARACTER_NO_HAVE.getDesc()));
    }
}
