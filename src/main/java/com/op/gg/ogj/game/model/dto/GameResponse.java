package com.op.gg.ogj.game.model.dto;

import com.op.gg.ogj.game.model.DeviceType;
import com.op.gg.ogj.game.model.GameFactoryMethod;
import com.op.gg.ogj.gameInfo.model.dto.GameInfoResponse;
import com.op.gg.ogj.map.model.dto.MapResponse;
import com.op.gg.ogj.ost.model.dto.OstResponse;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameResponse {

    private Long gameId;

    private String gameNm;

    private Integer price;

    private String brand;

    private DeviceType deviceType;

    private GameFactoryMethod gameType;

    private GameInfoResponse gameInfo;

    private List<OstResponse> osts;

    private List<MapResponse> maps;

    @QueryProjection
    public GameResponse(Long gameId, String gameNm, Integer price, String brand, DeviceType deviceType, GameFactoryMethod gameType, String gameInfo1, String gameInfo2) {
        this.gameId = gameId;
        this.gameNm = gameNm;
        this.price = price;
        this.brand = brand;
        this.deviceType = deviceType;
        this.gameType = gameType;
        gameInfo = GameInfoResponse.builder().gameInfo1(gameInfo1).gameInfo2(gameInfo2).build();
    }
}
