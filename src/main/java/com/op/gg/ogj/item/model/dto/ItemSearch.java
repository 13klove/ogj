package com.op.gg.ogj.item.model.dto;

import com.op.gg.ogj.item.model.ItemType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@ApiModel
@NoArgsConstructor
@AllArgsConstructor
public class ItemSearch {

    @ApiModelProperty(value = "characterId")
    private Long characterId;

    @ApiModelProperty(value = "itemId")
    private Long itemId;

    @ApiModelProperty(value = "itemNm")
    private String itemNm;

    @ApiModelProperty(value = "itemType")
    private ItemType itemType;

}
