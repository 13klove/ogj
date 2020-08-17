package com.op.gg.ogj.map.service;

import com.op.gg.ogj.map.model.dto.MapParam;
import com.op.gg.ogj.map.model.dto.MapResponse;
import com.op.gg.ogj.map.model.dto.MapSearch;
import com.op.gg.ogj.map.service.core.MapCoreService;
import com.op.gg.ogj.map.service.valid.MapValidService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MapService {

    private final MapCoreService mapCoreService;
    private final MapValidService mapValidService;

    @Transactional
    public Long createMap(MapParam mapParam){
        mapValidService.createMapValid(mapParam);
        return mapCoreService.createMap(mapParam);
    }

    @Transactional
    public Long updateMap(MapParam mapParam){
        mapValidService.updateMapValid(mapParam);
        return mapCoreService.updateMap(mapParam);
    }

    @Transactional
    public void delMap(MapParam mapParam){
        mapValidService.delMapValid(mapParam);
        mapCoreService.delMap(mapParam);
    }

    public Page<MapResponse> pageMap(MapSearch mapSearch, Pageable pageable){
        return mapCoreService.pageMap(mapSearch, pageable);
    }

    public MapResponse detailMap(MapSearch mapSearch){
        mapValidService.detailMap(mapSearch);
        return mapCoreService.detailMap(mapSearch);
    }

}
