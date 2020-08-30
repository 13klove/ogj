package com.op.gg.ogj.character.repository.queryDsl.entity;

import com.op.gg.ogj.character.model.entity.Character;

import java.util.List;

public interface CharacterDslRepository {

    Character findCharacterByCharacterId(Long characterId);

    Character findCharacterRelInfoByGameIdCharacterId(Long gameId, Long characterId);

    List<Character> findCharacterRelInfoByGameIdCharactersId(Long gameId, List<Long> charactersId);

}
