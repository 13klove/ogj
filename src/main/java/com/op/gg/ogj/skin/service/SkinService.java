package com.op.gg.ogj.skin.service;

import com.op.gg.ogj.skin.model.dto.SkinParam;
import com.op.gg.ogj.skin.model.dto.SkinResponse;
import com.op.gg.ogj.skin.model.dto.SkinSearch;
import com.op.gg.ogj.skin.service.core.SkinCoreService;
import com.op.gg.ogj.skin.service.valid.SkinValidService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SkinService {

    private final SkinCoreService skinCoreService;
    private final SkinValidService skinValidService;

    @Transactional
    public Long createSkin(SkinParam skinParam){
        skinValidService.createSkinValid(skinParam);
        return skinCoreService.createSkin(skinParam);
    }

    @Transactional
    public Long updateSkin(SkinParam skinParam){
        skinValidService.updateSkinValid(skinParam);
        return skinCoreService.updateSkin(skinParam);
    }

    public Page<SkinResponse> pageSkin(SkinSearch skinSearch, Pageable pageable){
        skinValidService.pageSkinValid(skinSearch);
        return skinCoreService.pageSkin(skinSearch, pageable);
    }

    public SkinResponse detailSkin(SkinSearch skinSearch){
        skinValidService.detailSkinValid(skinSearch);
        return skinCoreService.detailSkin(skinSearch);
    }

    @Transactional
    public void delSkin(SkinParam skinParam){
        skinValidService.delSkinValid(skinParam);
        skinCoreService.delSkin(skinParam);
    }

    @Transactional
    public void delSkins(SkinParam skinParam){
        skinValidService.delSkinsValid(skinParam);
        skinCoreService.delSkins(skinParam);
    }

}
