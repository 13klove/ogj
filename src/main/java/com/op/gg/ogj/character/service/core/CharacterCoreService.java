package com.op.gg.ogj.character.service.core;

import com.op.gg.ogj.character.model.dto.CharacterParam;
import com.op.gg.ogj.character.model.dto.CharacterResponse;
import com.op.gg.ogj.character.model.dto.CharacterSearch;
import com.op.gg.ogj.character.model.entity.Character;
import com.op.gg.ogj.character.repository.CharacterJpaRepository;
import com.op.gg.ogj.game.model.entity.Game;
import com.op.gg.ogj.game.repository.GameJpaRepository;
import com.op.gg.ogj.skill.model.entity.Skill;
import com.op.gg.ogj.skin.model.Skin;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CharacterCoreService {

    private final CharacterJpaRepository characterJpaRepository;
    private final GameJpaRepository gameJpaRepository;

    @Transactional
    public Long createCharacter(CharacterParam characterParam){
        Game game = gameJpaRepository.findById(characterParam.getGameId()).get();
        Character character = Character.createCharacter(characterParam.getCharacterNm(), characterParam.getCharacterType(), characterParam.getLife(), characterParam.getEnergy(), characterParam.getImgUrl());
        character.smGameChange(game);
        characterJpaRepository.save(character);
        return character.getCharacterId();
    }

    @Transactional
    public Long updateCharacter(CharacterParam characterParam){
        Character character = characterJpaRepository.findCharacterByCharacterId(characterParam.getCharacterId());
        character.updateCharacter(characterParam.getCharacterNm(), characterParam.getCharacterType(), characterParam.getLife(), characterParam.getEnergy(), characterParam.getImgUrl());
        return character.getCharacterId();
    }

    public Page<CharacterResponse> pageCharacter(CharacterSearch characterSearch, Pageable pageable){
        return characterJpaRepository.pageCharacter(characterSearch.getGameId(), characterSearch.getCharacterNm(), characterSearch.getCharacterType(), pageable);
    }

    public CharacterResponse detailCharacter(CharacterSearch characterSearch){
        return characterJpaRepository.detailCharacterByCharacterId(characterSearch.getCharacterId());
    }

    @Transactional
    public void delCharacter(CharacterParam characterParam) {
        Character character = characterJpaRepository.findCharacterRelInfoByGameIdCharacterId(characterParam.getGameId(), characterParam.getCharacterId());
        character.delCharacter();
        character.getSkills().forEach(Skill::delSkill);
        character.getSkins().forEach(Skin::delSkin);
        character.getItems().forEach(a->{a.getItemSpec().delItemSpec(); a.delItem();});
    }

    @Transactional
    public void delCharacters(CharacterParam characterParam) {
        List<Character> characters = characterJpaRepository.findCharacterRelInfoByGameIdCharactersId(characterParam.getGameId(), characterParam.getCharactersId());
        characters.forEach(a->{
            a.delCharacter();
            a.getSkills().forEach(Skill::delSkill);
            a.getSkins().forEach(Skin::delSkin);
            a.getItems().forEach(b->{b.getItemSpec().delItemSpec(); b.delItem();});
        });
    }
}
