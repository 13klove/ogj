package com.op.gg.ogj.game.model.dto;

import com.op.gg.ogj.game.model.DeviceType;
import com.op.gg.ogj.game.model.GameFactoryMethod;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel
public class GameParam {

    @ApiModelProperty(value = "게임id")
    private Long gameId;

    @ApiModelProperty(value = "게임이름", required = true)
    private String gameNm;

    @ApiModelProperty(value = "게임가격", required = true)
    private Integer price;

    @ApiModelProperty(value = "게임브렌드", required = true)
    private String brand;

    @ApiModelProperty(value = "게임 머신", required = true)
    private DeviceType deviceType;

    @ApiModelProperty(value = "게임 장르", required = true)
    private GameFactoryMethod gameType;

    //fps
    @ApiModelProperty(value = "fps전용")
    private Boolean storyYn;

    //aos
    @ApiModelProperty(value = "aos전용")
    private Boolean usemapYn;

    //prg
    @ApiModelProperty(value = "prg전용")
    private Boolean autoPlayYn;

    //rts
    @ApiModelProperty(value = "rts전용")
    private Boolean licenseYn;

    @ApiModelProperty(value = "게임 정보1", required = true)
    private String gameInfo1;

    @ApiModelProperty(value = "게임 정보2")
    private String gameInfo2;

}
