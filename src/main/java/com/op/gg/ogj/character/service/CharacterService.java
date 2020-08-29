package com.op.gg.ogj.character.service;

import com.op.gg.ogj.character.model.dto.CharacterParam;
import com.op.gg.ogj.character.model.dto.CharacterResponse;
import com.op.gg.ogj.character.model.dto.CharacterSearch;
import com.op.gg.ogj.character.service.core.CharacterCoreService;
import com.op.gg.ogj.character.service.valid.CharacterValidService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CharacterService {

    private final CharacterCoreService characterCoreService;
    private final CharacterValidService characterValidService;

    @Transactional
    public Long createCharacter(CharacterParam characterParam){
        characterValidService.createCharacterValid(characterParam);
        return characterCoreService.createCharacter(characterParam);
    }

    @Transactional
    public Long updateCharacter(CharacterParam characterParam){
        characterValidService.updateCharacterValid(characterParam);
        return characterCoreService.updateCharacter(characterParam);
    }

    public Page<CharacterResponse> pageCharacter(CharacterSearch characterSearch, Pageable pageable){
        characterValidService.pageCharacterValid(characterSearch);
        return characterCoreService.pageCharacter(characterSearch, pageable);
    }

    public CharacterResponse detailCharacter(CharacterSearch characterSearch){
        characterValidService.detailCharacterValid(characterSearch);
        return characterCoreService.detailCharacter(characterSearch);
    }

}
