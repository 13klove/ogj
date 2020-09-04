package com.op.gg.ogj.skill.service;

import com.op.gg.ogj.skill.model.dto.SkillParam;
import com.op.gg.ogj.skill.model.dto.SkillResponse;
import com.op.gg.ogj.skill.model.dto.SkillSearch;
import com.op.gg.ogj.skill.service.core.SkillCoreService;
import com.op.gg.ogj.skill.service.valid.SkillValidService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SkillService {

    private final SkillCoreService skillCoreService;
    private final SkillValidService skillValidService;

    @Transactional
    public Long createSkill(SkillParam skillParam){
        skillValidService.createSkillValid(skillParam);
        return skillCoreService.createSkill(skillParam);
    }

    @Transactional
    public Long updateSkill(SkillParam skillParam){
        skillValidService.updateSkillValid(skillParam);
        return skillCoreService.updateSkill(skillParam);
    }

    public List<SkillResponse> listSkill(SkillSearch skillSearch){
        skillValidService.listSkillValid(skillSearch);
        return skillCoreService.listSkill(skillSearch);
    }

    public SkillResponse detailSkill(SkillSearch skillSearch){
        skillValidService.detailSkillValid(skillSearch);
        return skillCoreService.detailSkill(skillSearch);
    }

    @Transactional
    public void delSkill(SkillParam skillParam){
        skillValidService.delSkillValid(skillParam);
        skillCoreService.delSkill(skillParam);
    }

    @Transactional
    public void delSkills(SkillParam skillParam){
        skillValidService.delSkillsValid(skillParam);
        skillCoreService.delSkillsValid(skillParam);
    }

}
