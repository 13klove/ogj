package com.op.gg.ogj.skill.model.dto;

import com.op.gg.ogj.skill.model.SkillType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SkillSearch {

    private Long sillId;

    private Long characterId;

    private String skillNm;

    private SkillType skillType;

}
