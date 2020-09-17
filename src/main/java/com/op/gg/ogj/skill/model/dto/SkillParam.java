package com.op.gg.ogj.skill.model.dto;

import com.op.gg.ogj.skill.model.SkillType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@ApiModel
@NoArgsConstructor
@AllArgsConstructor
public class SkillParam {

    @ApiModelProperty(value = "skillId")
    private Long skillId;

    @ApiModelProperty(value = "skillsId")
    private List<Long> skillsId;

    @ApiModelProperty(value = "characterId", required = true)
    private Long characterId;

    @ApiModelProperty(value = "skillNm", required = true)
    private String skillNm;

    @ApiModelProperty(value = "useEnergy", required = true)
    private Integer useEnergy;

    @ApiModelProperty(value = "useLife", required = true)
    private Integer useLife;

    @ApiModelProperty(value = "damage", required = true)
    private Integer damage;

    @ApiModelProperty(value = "skillType", required = true)
    private SkillType skillType;

    @ApiModelProperty(value = "ulimateYn", required = true)
    private Boolean ulimateYn;

    @ApiModelProperty(value = "imgUrl")
    private String imgUrl;

}
