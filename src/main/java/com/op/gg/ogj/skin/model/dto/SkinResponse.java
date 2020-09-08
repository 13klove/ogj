package com.op.gg.ogj.skin.model.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class SkinResponse {

    private Long skinId;

    private String skinNm;

    private Long price;

    @QueryProjection
    public SkinResponse(Long skinId, String skinNm, Long price) {
        this.skinId = skinId;
        this.skinNm = skinNm;
        this.price = price;
    }
}
