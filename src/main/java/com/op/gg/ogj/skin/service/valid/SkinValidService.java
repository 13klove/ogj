package com.op.gg.ogj.skin.service.valid;

import com.op.gg.ogj.character.model.dto.CharacterParam;
import com.op.gg.ogj.character.model.entity.Character;
import com.op.gg.ogj.character.repository.CharacterJpaRepository;
import com.op.gg.ogj.character.valid.CharacterValid;
import com.op.gg.ogj.config.exception.domain.ParamValidException;
import com.op.gg.ogj.skin.model.dto.SkinParam;
import com.op.gg.ogj.skin.model.dto.SkinSearch;
import com.op.gg.ogj.skin.model.entity.Skin;
import com.op.gg.ogj.skin.repository.SkinJpaRepository;
import com.op.gg.ogj.skin.valid.SkinValid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SkinValidService {
    
    private final SkinJpaRepository skinJpaRepository;
    private final CharacterJpaRepository characterJpaRepository;
    
    public void createSkinValid(SkinParam skinParam) {
        createUpdateValid(skinParam);

        Skin skin = skinJpaRepository.findSkinByCharacter_CharacterIdAndSkinNmAndActYnTrue(skinParam.getCharacterId(), skinParam.getSkinNm());
        if(skin!=null) throw new ParamValidException(SkinValid.SKIN_EXIT_SKIN.getDesc());
    }

    public void updateSkinValid(SkinParam skinParam) {
        createUpdateValid(skinParam);

        SkinValid.SKIN_SKIN_ID_LOCK.validLogic(skinParam);

        Skin skin = skinJpaRepository.findSkinByCharacter_CharacterIdAndSkinNmAndSkinIdIsNotAndActYnTrue(skinParam.getCharacterId(), skinParam.getSkinNm(), skinParam.getSkinId());
        if(skin!=null) throw new ParamValidException(SkinValid.SKIN_EXIT_SKIN.getDesc());
    }

    public void pageSkinValid(SkinSearch skinSearch) {
        CharacterParam characterParam = CharacterParam.builder().characterId(skinSearch.getCharacterId()).build();

        CharacterValid.CHARACTER_ID_LOCK.validLogic(characterParam);
    }

    public void detailSkinValid(SkinSearch skinSearch) {
        SkinValid.SKIN_SKIN_ID_LOCK.validLogic(SkinParam.builder().skinId(skinSearch.getSkinId()).build());
    }

    public void delSkinValid(SkinParam skinParam) {
        SkinValid.SKIN_SKIN_ID_LOCK.validLogic(skinParam);
    }

    public void delSkinsValid(SkinParam skinParam) {
        SkinValid.SKIN_SKINS_ID_LOCK.validLogic(skinParam);
    }

    private void createUpdateValid(SkinParam skinParam) {
        CharacterParam characterParam = CharacterParam.builder().characterId(skinParam.getCharacterId()).build();

        CharacterValid.CHARACTER_ID_LOCK.validLogic(characterParam);
        SkinValid.SKIN_SKIN_NM_LOCK.validLogic(skinParam);
        SkinValid.SKIN_PRICE_LOCK.validLogic(skinParam);

        Optional<Character> optionalCharacter = characterJpaRepository.findById(skinParam.getCharacterId());
        optionalCharacter.orElseThrow(() -> new ParamValidException(CharacterValid.CHARACTER_NO_HAVE.getDesc()));
    }

}
