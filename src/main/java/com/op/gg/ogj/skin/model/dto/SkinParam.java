package com.op.gg.ogj.skin.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Builder
@ApiModel
@AllArgsConstructor
@RequiredArgsConstructor
public class SkinParam {

    @ApiModelProperty(notes = "characterId", required = true)
    private Long characterId;

    @ApiModelProperty(notes = "skinId")
    private Long skinId;

    @ApiModelProperty(notes = "skinsId")
    private List<Long> skinsId;

    @ApiModelProperty(notes = "skinNm", required = true)
    private String skinNm;

    @ApiModelProperty(notes = "price", required = true)
    private Long price;

}
