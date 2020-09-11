package com.op.gg.ogj.character.service.valid;

import com.op.gg.ogj.character.model.dto.CharacterParam;
import com.op.gg.ogj.character.model.dto.CharacterSearch;
import com.op.gg.ogj.character.model.entity.Character;
import com.op.gg.ogj.character.repository.CharacterJpaRepository;
import com.op.gg.ogj.character.valid.CharacterValid;
import com.op.gg.ogj.game.model.entity.Game;
import com.op.gg.ogj.game.repository.GameJpaRepository;
import com.op.gg.ogj.game.valied.GameValid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CharacterValidService {

    private final GameJpaRepository gameJpaRepository;
    private final CharacterJpaRepository characterJpaRepository;

    public void createCharacterValid(CharacterParam characterParam){
        createUpdate(characterParam);
        Character character = characterJpaRepository.findCharacterByGameGameIdAndCharacterNm(characterParam.getGameId(), characterParam.getCharacterNm());
        CharacterValid.CHARACTER_NAME_EXIST.validLogic(character);
    }

    public void updateCharacterValid(CharacterParam characterParam){
        createUpdate(characterParam);
        CharacterValid.CHARACTER_ID_LOCK.validLogic(characterParam.getCharacterId());
        Character character = characterJpaRepository.findCharacterByGameGameIdAndCharacterIdIsNotAndCharacterNm(characterParam.getGameId(), characterParam.getCharacterId(), characterParam.getCharacterNm());
        CharacterValid.CHARACTER_NAME_EXIST.validLogic(character);
    }

    public void pageCharacterValid(CharacterSearch characterSearch){ GameValid.GAME_ID_LOCK.validLogic(characterSearch.getGameId()); }

    public void detailCharacterValid(CharacterSearch characterSearch){ CharacterValid.CHARACTER_ID_LOCK.validLogic(characterSearch.getCharacterId()); }

    public void delCharacterValid(CharacterParam characterParam) {
        GameValid.GAME_ID_LOCK.validLogic(characterParam.getGameId());
        CharacterValid.CHARACTER_ID_LOCK.validLogic(characterParam.getCharacterId());

        Optional<Character> character = characterJpaRepository.findCharacterByGameGameIdAndCharacterId(characterParam.getGameId(), characterParam.getCharacterId());
        CharacterValid.CHARACTER_NO_HAVE.validLogic(character);
    }

    public void delCharactersValid(CharacterParam characterParam) {
        GameValid.GAME_ID_LOCK.validLogic(characterParam.getGameId());
        CharacterValid.CHARACTER_IDS_LOCK.validLogic(characterParam.getCharactersId());
    }

    private void createUpdate(CharacterParam characterParam) {
        GameValid.GAME_ID_LOCK.validLogic(characterParam.getGameId());
        CharacterValid.CHARACTER_TYPE_LOCK.validLogic(characterParam.getCharacterType());
        CharacterValid.CHARACTER_LIFE_LOCK.validLogic(characterParam.getLife());
        CharacterValid.CHARACTER_ENERGY_LOCK.validLogic(characterParam.getEnergy());
        CharacterValid.CHARACTER_NAME_LOCK.validLogic(characterParam.getCharacterNm());

        Optional<Game> game = gameJpaRepository.findById(characterParam.getGameId());
        GameValid.GAME_NO_HAVE.validLogic(game);
    }

}
