package com.op.gg.ogj.ost.model.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OstResponse {

    private Long ostId;

    private Long mapId;

    private String ostNm;

    private Boolean actYn;

    @QueryProjection
    public OstResponse(Long mapId, String ostNm) {
        this.mapId = mapId;
        this.ostNm = ostNm;
    }

}
