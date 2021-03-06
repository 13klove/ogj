package com.op.gg.ogj.map.model.dto;

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
public class MapParam {

    @ApiModelProperty(value = "게임id", required = true)
    private Long gameId;

    @ApiModelProperty(value = "맵id")
    private Long mapId;

    @ApiModelProperty(value = "맵이름", required = true)
    private String mapNm;

    @ApiModelProperty(value = "맵id 묶음")
    private List<Long> mapsId;

}
