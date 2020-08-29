package com.op.gg.ogj.character.model.dto;

import com.op.gg.ogj.character.model.CharacterType;
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
public class CharacterSearch {

    @ApiModelProperty(value = "gameId", notes = "gameId")
    private Long gameId;

    @ApiModelProperty(value = "characterNm", notes = "characterNm")
    private String characterNm;

    @ApiModelProperty(value = "characterType", notes = "characterType")
    private CharacterType characterType;

}
