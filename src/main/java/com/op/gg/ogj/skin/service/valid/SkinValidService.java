package com.op.gg.ogj.skin.service.valid;

import com.op.gg.ogj.character.model.entity.Character;
import com.op.gg.ogj.character.repository.CharacterJpaRepository;
import com.op.gg.ogj.character.valid.CharacterValid;
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
        SkinValid.SKIN_EXIT_SKIN.validLogic(skin);
    }

    public void updateSkinValid(SkinParam skinParam) {
        SkinValid.SKIN_SKIN_ID_LOCK.validLogic(skinParam.getSkinId());
        createUpdateValid(skinParam);
        Skin skin = skinJpaRepository.findSkinByCharacter_CharacterIdAndSkinNmAndSkinIdIsNotAndActYnTrue(skinParam.getCharacterId(), skinParam.getSkinNm(), skinParam.getSkinId());
        SkinValid.SKIN_EXIT_SKIN.validLogic(skin);
    }

    public void pageSkinValid(SkinSearch skinSearch) { CharacterValid.CHARACTER_ID_LOCK.validLogic(skinSearch.getCharacterId()); }

    public void detailSkinValid(SkinSearch skinSearch) { SkinValid.SKIN_SKIN_ID_LOCK.validLogic(skinSearch.getSkinId()); }

    public void delSkinValid(SkinParam skinParam) {
        SkinValid.SKIN_SKIN_ID_LOCK.validLogic(skinParam.getSkinId());
    }

    public void delSkinsValid(SkinParam skinParam) {
        SkinValid.SKIN_SKINS_ID_LOCK.validLogic(skinParam.getSkinsId());
    }

    private void createUpdateValid(SkinParam skinParam) {
        CharacterValid.CHARACTER_ID_LOCK.validLogic(skinParam.getCharacterId());
        SkinValid.SKIN_SKIN_NM_LOCK.validLogic(skinParam.getSkinNm());
        SkinValid.SKIN_PRICE_LOCK.validLogic(skinParam.getPrice());

        Optional<Character> optionalCharacter = characterJpaRepository.findById(skinParam.getCharacterId());
        CharacterValid.CHARACTER_NO_HAVE.validLogic(optionalCharacter);
    }

}
