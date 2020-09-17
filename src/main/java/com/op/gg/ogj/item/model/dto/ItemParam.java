package com.op.gg.ogj.item.model.dto;

import com.op.gg.ogj.item.model.ItemType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@ApiModel
@NoArgsConstructor
@AllArgsConstructor
public class ItemParam {

    @ApiModelProperty(value = "characterId", required = true)
    private Long characterId;

    @ApiModelProperty(value = "itemId")
    private Long itemId;

    @ApiModelProperty(value = "itemIds")
    private List<Long> itemIds;

    @ApiModelProperty(value = "itemNm", required = true)
    private String itemNm;

    @ApiModelProperty(value = "itemType", required = true)
    private ItemType itemType;

    @ApiModelProperty(value = "price", required = true)
    private Integer price;

    @ApiModelProperty(value = "isInfo", required = true)
    private String isInfo;

    @ApiModelProperty(value = "damageRate", required = true)
    private Integer damageRate;

    @ApiModelProperty(value = "defenseRate", required = true)
    private Integer defenseRate;

}
