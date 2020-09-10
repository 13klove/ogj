package com.op.gg.ogj.item.model.dto;

import com.op.gg.ogj.item.model.ItemType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class ItemResponse {

    private Long itemId;

    private String itemNm;

    private ItemType itemType;

    private String isInfo;

    private Integer damageRate;

    private Integer defenseRate;

    @QueryProjection
    public ItemResponse(Long itemId, String itemNm, ItemType itemType, String isInfo, Integer damageRate, Integer defenseRate) {
        this.itemId = itemId;
        this.itemNm = itemNm;
        this.itemType = itemType;
        this.isInfo = isInfo;
        this.damageRate = damageRate;
        this.defenseRate = defenseRate;
    }
}
