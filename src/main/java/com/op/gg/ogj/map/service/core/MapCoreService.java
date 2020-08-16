package com.op.gg.ogj.map.service.core;

import com.op.gg.ogj.game.model.entity.Game;
import com.op.gg.ogj.game.repository.GameJpaRepository;
import com.op.gg.ogj.map.model.dto.MapParam;
import com.op.gg.ogj.map.model.dto.MapResponse;
import com.op.gg.ogj.map.model.dto.MapSearch;
import com.op.gg.ogj.map.model.entity.Map;
import com.op.gg.ogj.map.repository.MapJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MapCoreService {

    private final MapJpaRepository mapJpaRepository;
    private final GameJpaRepository gameJpaRepository;

    @Transactional
    public Long createMap(MapParam mapParam) {
        Map map = Map.createMap(mapParam.getMapNm());
        Game game = gameJpaRepository.findById(mapParam.getGameId()).get();
        map.smGameChange(game);

        mapJpaRepository.save(map);
        return map.getMapId();
    }

    @Transactional
    public Long updateMap(MapParam mapParam) {
        Map map = mapJpaRepository.findById(mapParam.getMapId()).get();
        map.updateMap(mapParam.getMapNm());
        return map.getMapId();
    }

    @Transactional
    public void delMap(MapSearch mapSearch) {
        if(!mapSearch.getMapsId().isEmpty()) {
            List<Map> maps = mapJpaRepository.findMapsByMapIdIn(mapSearch.getMapsId());
            mapJpaRepository.deleteAll(maps);
        }else{
            Map map = mapJpaRepository.findById(mapSearch.getMapId()).get();
            mapJpaRepository.delete(map);
        }
    }

    public Page<MapResponse> pageMap(MapSearch mapSearch, Pageable pageable) {
        return mapJpaRepository.findPageMap(mapSearch, pageable);
    }

    public MapResponse detailMap(MapSearch mapSearch) {
        return mapJpaRepository.findDetailMapByMapId(mapSearch);
    }
}
