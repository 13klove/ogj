package com.op.gg.ogj.map.repository.queryDsl.entity;

import com.op.gg.ogj.map.model.entity.Map;

public interface MapDslRepository {

    Map findMapByGameIdMapId(Long gameId, Long mapId);

}
