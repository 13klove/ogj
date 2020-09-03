package com.op.gg.ogj.skill.model.dto;

import com.op.gg.ogj.skill.model.SkillType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class SkillResponse {

    private Long skillId;

    private Long characterId;

    private String skillNm;

    private Integer useEnergy;

    private Integer useLife;

    private Integer damage;

    private SkillType skillType;

    private Boolean ulimateYn;

    private String imgUrl;

    @QueryProjection
    public SkillResponse(Long skillId, Long characterId, String skillNm, Integer useEnergy, Integer useLife, Integer damage, SkillType skillType, Boolean ulimateYn, String imgUrl) {
        this.skillId = skillId;
        this.characterId = characterId;
        this.skillNm = skillNm;
        this.useEnergy = useEnergy;
        this.useLife = useLife;
        this.damage = damage;
        this.skillType = skillType;
        this.ulimateYn = ulimateYn;
        this.imgUrl = imgUrl;
    }
}
