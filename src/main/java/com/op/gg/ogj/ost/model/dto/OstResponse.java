package com.op.gg.ogj.ost.model.dto;

import com.op.gg.ogj.game.model.dto.GameResponse;
import com.op.gg.ogj.map.model.dto.MapResponse;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OstResponse {

    private Long ostId;

    private MapResponse mapResponse;

    private GameResponse gameResponse;

    private String ostNm;

    private Boolean actYn;

    @QueryProjection
    public OstResponse(Long ostId, String ostNm) {
        this.ostId = ostId;
        this.ostNm = ostNm;
    }

    @QueryProjection
    public OstResponse(Long ostId, String ostNm, Long mapId, String mapNm, Long gameId) {
        this.ostId = ostId;
        this.ostNm = ostNm;
        this.mapResponse = MapResponse.builder().mapId(mapId).mapNm(mapNm).build();
        this.gameResponse = GameResponse.builder().gameId(gameId).build();
    }

    @QueryProjection
    public OstResponse(Long ostId, String ostNm, Long mapId, String mapNm) {
        this.ostId = ostId;
        this.ostNm = ostNm;
        this.mapResponse = MapResponse.builder().mapId(mapId).mapNm(mapNm).build();
    }

}
