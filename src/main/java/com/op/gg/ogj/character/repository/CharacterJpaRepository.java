package com.op.gg.ogj.character.repository;

import com.op.gg.ogj.character.model.entity.Character;
import com.op.gg.ogj.character.repository.queryDsl.dto.CharacterDtoDslRepository;
import com.op.gg.ogj.character.repository.queryDsl.entity.CharacterDslRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CharacterJpaRepository extends JpaRepository<Character, Long>, CharacterDtoDslRepository, CharacterDslRepository {

    Character findCharacterByGameGameIdAndCharacterNm(Long gameId, String characterNm);

    Character findCharacterByGameGameIdAndCharacterIdIsNotAndCharacterNm(Long gameId, Long characterId, String characterNm);

    Optional<Character> findCharacterByGameGameIdAndCharacterId(Long gameId, Long characterId);

}
