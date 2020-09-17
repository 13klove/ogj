package com.op.gg.ogj.skin.model.dto;

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
public class SkinSearch {

    @ApiModelProperty(notes = "characterId", required = true)
    private Long characterId;

    @ApiModelProperty(notes = "skinId")
    private Long skinId;

    @ApiModelProperty(notes = "skinNm")
    private String skinNm;

}
