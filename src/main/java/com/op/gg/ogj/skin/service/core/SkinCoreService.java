package com.op.gg.ogj.skin.service.core;

import com.op.gg.ogj.character.model.entity.Character;
import com.op.gg.ogj.character.repository.CharacterJpaRepository;
import com.op.gg.ogj.skin.model.dto.SkinParam;
import com.op.gg.ogj.skin.model.dto.SkinResponse;
import com.op.gg.ogj.skin.model.dto.SkinSearch;
import com.op.gg.ogj.skin.model.entity.Skin;
import com.op.gg.ogj.skin.repository.SkinJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SkinCoreService {

    private final SkinJpaRepository skinJpaRepository;
    private final CharacterJpaRepository characterJpaRepository;

    @Transactional
    public Long createSkin(SkinParam skinParam) {
        Character character = characterJpaRepository.findById(skinParam.getCharacterId()).get();

        Skin skin = Skin.createSkin(skinParam.getSkinNm(), skinParam.getPrice());
        skin.smCharacterChange(character);

        skinJpaRepository.save(skin);

        return skin.getSkinId();
    }

    @Transactional
    public Long updateSkin(SkinParam skinParam) {
        Skin skin = skinJpaRepository.findSkinAndCharacterBySkinId(skinParam.getSkinId());
        skin.updateSkin(skinParam.getSkinNm(), skinParam.getPrice());
        return skin.getSkinId();
    }

    public Page<SkinResponse> pageSkin(SkinSearch skinSearch, Pageable pageable) {
        return skinJpaRepository.pageSkin(skinSearch.getCharacterId(), skinSearch.getSkinNm(), pageable);
    }

    public SkinResponse detailSkin(SkinSearch skinSearch) {
        return skinJpaRepository.detailSkin(skinSearch.getSkinId());
    }

    @Transactional
    public void delSkin(SkinParam skinParam) {
        Skin skin = skinJpaRepository.findSkinAndCharacterBySkinId(skinParam.getSkinId());
        skin.delSkin();
    }

    @Transactional
    public void delSkins(SkinParam skinParam) {
        List<Skin> skins = skinJpaRepository.findSkinAndCharacterBySkinIds(skinParam.getSkinsId());
        skins.forEach(Skin::delSkin);
    }

}
