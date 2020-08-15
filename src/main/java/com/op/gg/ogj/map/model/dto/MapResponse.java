package com.op.gg.ogj.map.model.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MapResponse {

    private Long mapId;

    private String mapNm;

    private Boolean actYn;

    @QueryProjection
    public MapResponse(String mapNm) {
        this.mapNm = mapNm;
    }
}
