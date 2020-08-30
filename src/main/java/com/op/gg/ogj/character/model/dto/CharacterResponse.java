package com.op.gg.ogj.character.model.dto;

import com.op.gg.ogj.character.model.CharacterType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
//@AllArgsConstructor
public class CharacterResponse {

    private Long gameId;

    private Long characterId;

    private String characterNm;

    private CharacterType characterType;

    private Integer life;

    private Integer energy;

    private String imgUrl;

    @QueryProjection
    public CharacterResponse(Long gameId, Long characterId, String characterNm, CharacterType characterType, Integer life, Integer energy, String imgUrl) {
        this.gameId = gameId;
        this.characterId = characterId;
        this.characterNm = characterNm;
        this.characterType = characterType;
        this.life = life;
        this.energy = energy;
        this.imgUrl = imgUrl;
    }
}
