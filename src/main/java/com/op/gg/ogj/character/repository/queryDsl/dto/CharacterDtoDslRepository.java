package com.op.gg.ogj.character.repository.queryDsl.dto;

import com.op.gg.ogj.character.model.CharacterType;
import com.op.gg.ogj.character.model.dto.CharacterResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CharacterDtoDslRepository {

    Page<CharacterResponse> pageCharacter(Long gameId, String characterNm, CharacterType characterType, Pageable pageable);

    CharacterResponse detailCharacterByCharacterId(Long characterId);

}
