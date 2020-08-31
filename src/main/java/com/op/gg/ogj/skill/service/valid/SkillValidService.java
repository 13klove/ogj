package com.op.gg.ogj.skill.service.valid;

import com.op.gg.ogj.skill.model.dto.SkillParam;
import com.op.gg.ogj.skill.model.dto.SkillSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SkillValidService {
    
    public void createSkillValid(SkillParam skillParam) {
    }

    public void updateSkillValid(SkillParam skillParam) {
    }

    public void listSkillValid(SkillSearch skillSearch) {
    }

    public void detailSkillValid(SkillSearch skillSearch) {
    }

    public void delSkillValid(SkillParam skillParam) {
    }

    public void delSkillsValid(SkillParam skillParam) {
    }
}
