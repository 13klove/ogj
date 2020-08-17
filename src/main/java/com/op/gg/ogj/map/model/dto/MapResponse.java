package com.op.gg.ogj.map.model.dto;

import com.op.gg.ogj.map.model.entity.Map;
import com.op.gg.ogj.ost.model.dto.OstResponse;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MapResponse {

    private Long mapId;

    private String mapNm;

    private Boolean actYn;

    private List<OstResponse> osts;

    @QueryProjection
    public MapResponse(String mapNm) {
        this.mapNm = mapNm;
    }

    @QueryProjection
    public MapResponse(Long mapId, String mapNm) {
        this.mapId = mapId;
        this.mapNm = mapNm;
    }

    public MapResponse(Map mapEntiry){
        this.mapNm = mapEntiry.getMapNm();
        this.osts = mapEntiry.getOsts().stream().map(a->OstResponse.builder().ostNm(a.getOstNm()).build()).collect(Collectors.toList());
    }


}
