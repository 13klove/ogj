package com.op.gg.ogj.skill.model.dto;

import com.op.gg.ogj.skill.model.SkillType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@ApiModel
@NoArgsConstructor
@AllArgsConstructor
public class SkillSearch {

    @ApiModelProperty(notes = "skillId")
    private Long skillId;

    @ApiModelProperty(notes = "characterId")
    private Long characterId;

    @ApiModelProperty(notes = "skillNm")
    private String skillNm;

    @ApiModelProperty(notes = "skillType")
    private SkillType skillType;

    @ApiModelProperty(notes = "ulimateYn")
    private Boolean ulimateYn;

}
