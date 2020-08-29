package com.op.gg.ogj.character.repository.queryDsl.entity;

import com.op.gg.ogj.character.model.entity.Character;

public interface CharacterDslRepository {

    Character findCharacterByCharacterId(Long characterId);

}
