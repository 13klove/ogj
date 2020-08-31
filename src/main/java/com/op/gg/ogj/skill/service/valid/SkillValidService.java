package com.op.gg.ogj.skill.service.valid;

import com.op.gg.ogj.skill.model.dto.SkillParam;
import com.op.gg.ogj.skill.model.dto.SkillSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SkillValidService {
    
    public void createSkillValid(SkillParam skillParam) {
    }

    public void updateSkillValid(SkillParam skillParam) {
    }

    public void listSkillValid(SkillSearch skillSearch) {
    }
}
