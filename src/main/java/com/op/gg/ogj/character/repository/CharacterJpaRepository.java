package com.op.gg.ogj.character.repository;

import com.op.gg.ogj.character.repository.queryDsl.dto.CharacterDtoDslRepository;
import com.op.gg.ogj.character.repository.queryDsl.entity.CharacterDslRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.op.gg.ogj.character.model.entity.Character;

public interface CharacterJpaRepository extends JpaRepository<Character, Long>, CharacterDtoDslRepository, CharacterDslRepository {

    Character findCharacterByGameGameIdAndCharacterNm(Long gameId, String characterNm);

}
