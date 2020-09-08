package com.op.gg.ogj.skin.repository.queryDsl.entity;

import com.op.gg.ogj.skin.model.entity.Skin;

import java.util.List;

public interface SkinDslRepository {

    Skin findSkinAndCharacterBySkinId(Long skinId);

    List<Skin> findSkinAndCharacterBySkinIds(List<Long> skinIds);

}
