package com.op.gg.ogj.ost.model.dto;

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
public class OstSearch {

    @ApiModelProperty(value = "ostNm")
    private String ostNm;

    @ApiModelProperty(value = "mapNm")
    private String mapNm;

    @ApiModelProperty(value = "gameId")
    private Long gameId;

    @ApiModelProperty(value = "ostId")
    private Long ostId;

}
