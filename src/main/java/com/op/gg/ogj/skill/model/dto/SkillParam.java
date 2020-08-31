package com.op.gg.ogj.skill.model.dto;

import com.op.gg.ogj.character.model.entity.Character;
import com.op.gg.ogj.config.baseDate.BaseDate;
import com.op.gg.ogj.skill.model.SkillType;
import lombok.*;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SkillParam {

    private Long sillId;

    private Long characterId;

    private String skillNm;

    private Integer useEnergy;

    private Integer useLife;

    private Integer damage;

    private SkillType skillType;

    private Boolean ulimateYn;

    private String imgUrl;

}
