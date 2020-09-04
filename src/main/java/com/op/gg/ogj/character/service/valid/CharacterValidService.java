package com.op.gg.ogj.character.service.valid;

import com.op.gg.ogj.character.model.dto.CharacterParam;
import com.op.gg.ogj.character.model.dto.CharacterSearch;
import com.op.gg.ogj.character.model.entity.Character;
import com.op.gg.ogj.character.repository.CharacterJpaRepository;
import com.op.gg.ogj.character.valid.CharacterValid;
import com.op.gg.ogj.config.exception.domain.ParamValidException;
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
        if(characterParam.getGameId() == null) throw new ParamValidException(GameValid.GAME_ID_LOCK.getDesc());
        CharacterValid.CHARACTER_TYPE_LOCK.validLogic(characterParam);
        CharacterValid.CHARACTER_LIFE_LOCK.validLogic(characterParam);
        CharacterValid.CHARACTER_ENERGY_LOCK.validLogic(characterParam);
        CharacterValid.CHARACTER_NAME_LOCK.validLogic(characterParam);
        //if(characterParam.getCharacterType() == null) throw new ParamValidException(CharacterValid.CHARACTER_TYPE_LOCK.getDesc());
        //if(characterParam.getLife() == null) throw new ParamValidException(CharacterValid.CHARACTER_LIFE_LOCK.getDesc());
        //if(characterParam.getEnergy() == null) throw new ParamValidException(CharacterValid.CHARACTER_ENERGY_LOCK.getDesc());
        //if(!StringUtils.hasText(characterParam.getCharacterNm())) throw new ParamValidException(CharacterValid.CHARACTER_NAME_LOCK.getDesc());

        Optional<Game> game = gameJpaRepository.findById(characterParam.getGameId());
        game.orElseThrow(() -> new ParamValidException(GameValid.GAME_LOCK.getDesc()));

        Character character = characterJpaRepository.findCharacterByGameGameIdAndCharacterNm(characterParam.getGameId(), characterParam.getCharacterNm());
        if(character != null) throw new ParamValidException(CharacterValid.CHARACTER_NAME_EXIST.getDesc());
    }

    public void updateCharacterValid(CharacterParam characterParam){
        if(characterParam.getGameId() == null) throw new ParamValidException(GameValid.GAME_ID_LOCK.getDesc());

        //if(characterParam.getCharacterId() == null) throw new ParamValidException(CharacterValid.CHARACTER_ID_LOCK.getDesc());
        CharacterValid.CHARACTER_ID_LOCK.validLogic(characterParam);
        CharacterValid.CHARACTER_TYPE_LOCK.validLogic(characterParam);
        CharacterValid.CHARACTER_LIFE_LOCK.validLogic(characterParam);
        CharacterValid.CHARACTER_ENERGY_LOCK.validLogic(characterParam);
        CharacterValid.CHARACTER_NAME_LOCK.validLogic(characterParam);

        Character character = characterJpaRepository.findCharacterByGameGameIdAndCharacterIdIsNotAndCharacterNm(characterParam.getGameId(), characterParam.getCharacterId(), characterParam.getCharacterNm());
        if(character != null) throw new ParamValidException(CharacterValid.CHARACTER_NAME_EXIST.getDesc());
    }

    public void pageCharacterValid(CharacterSearch characterSearch){
        if(characterSearch.getGameId() == null) throw new ParamValidException(GameValid.GAME_ID_LOCK.getDesc());
    }

    public void detailCharacterValid(CharacterSearch characterSearch){
        //if(characterSearch.getCharacterId() == null) throw new ParamValidException(CharacterValid.CHARACTER_ID_LOCK.getDesc());

        CharacterParam characterParam = CharacterParam.builder().characterId(characterSearch.getCharacterId()).build();
        CharacterValid.CHARACTER_ID_LOCK.validLogic(characterParam);
    }

    public void delCharacterValid(CharacterParam characterParam) {
        if(characterParam.getGameId() == null) throw new ParamValidException(GameValid.GAME_ID_LOCK.getDesc());
        //if(characterParam.getCharacterId() == null) throw new ParamValidException(CharacterValid.CHARACTER_ID_LOCK.getDesc());
        CharacterValid.CHARACTER_ID_LOCK.validLogic(characterParam);

        Optional<Character> character = characterJpaRepository.findCharacterByGameGameIdAndCharacterId(characterParam.getGameId(), characterParam.getCharacterId());
        character.orElseThrow(() -> new ParamValidException(CharacterValid.CHARACTER_NO_HAVE.getDesc()));
    }

    public void delCharactersValid(CharacterParam characterParam) {
        if(characterParam.getGameId() == null) throw new ParamValidException(GameValid.GAME_ID_LOCK.getDesc());
        CharacterValid.CHARACTER_IDS_LOCK.validLogic(characterParam);
    }
}
