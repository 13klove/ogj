package com.op.gg.ogj.skin.repository.queryDsl.dto;

import com.op.gg.ogj.skin.model.dto.SkinResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SkinDtoDslRepository {

    Page<SkinResponse> pageSkin(Long characterId, String skinNm, Pageable pageable);

    SkinResponse detailSkin(Long skinId);

}
