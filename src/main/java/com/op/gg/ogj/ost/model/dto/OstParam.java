package com.op.gg.ogj.ost.model.dto;

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
public class OstParam {

    @ApiModelProperty(value = "ostId", required = true)
    private Long ostId;

    @ApiModelProperty(value = "ost명", required = true)
    private String ostNm;

    @ApiModelProperty(value = "삭제여부")
    private Boolean actYn;

    @ApiModelProperty(value = "게임id", required = true)
    private Long gameId;

    @ApiModelProperty(value = "맵id")
    private Long mapId;

    @ApiModelProperty(value = "맵Sid")
    private List<Long> mapsId;

    @ApiModelProperty(value = "맵Sid")
    private List<Long> ostsId;

}
