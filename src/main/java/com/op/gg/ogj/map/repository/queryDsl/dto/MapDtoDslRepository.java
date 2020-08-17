package com.op.gg.ogj.map.repository.queryDsl.dto;

import com.op.gg.ogj.map.model.dto.MapResponse;
import com.op.gg.ogj.map.model.dto.MapSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MapDtoDslRepository {

    Page<MapResponse> findPageMap(MapSearch mapSearch, Pageable pageable);

    MapResponse findDetailMapByMapId(MapSearch mapSearch);
}
