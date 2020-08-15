package com.op.gg.ogj.game.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.op.gg.ogj.game.model.DeviceType;
import com.op.gg.ogj.game.model.GameFactoryMethod;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel
public class GameSearch {

    @ApiModelProperty(value = "게임 id")
    private Long gameId;

    @ApiModelProperty(value = "게임 이름")
    private String gameNm;

    @ApiModelProperty(value = "시작 가격 범위")
    private Integer startPrice;

    @ApiModelProperty(value = "끝 가격 범위")
    private Integer endPrice;

    @ApiModelProperty(value = "시작일")
    @JsonFormat(pattern = "YYYYmmdd")
    private LocalDate startDate;

    @ApiModelProperty(value = "종료일")
    @JsonFormat(pattern = "YYYYmmdd")
    private LocalDate endDate;

    @ApiModelProperty(value = "브랜드")
    private String brand;

    @ApiModelProperty(value = "기기 종류")
    private DeviceType deviceType;

    @ApiModelProperty(value = "게임 타입")
    private GameFactoryMethod gameType;

    @ApiModelProperty(value = "페이지: pgae=0&size=20")
    private Pageable pageable;

}
