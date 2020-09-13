package com.op.gg.ogj.map.service.valid;

import com.op.gg.ogj.character.valid.CharacterValid;
import com.op.gg.ogj.game.valied.GameValid;
import com.op.gg.ogj.map.model.dto.MapParam;
import com.op.gg.ogj.map.model.dto.MapSearch;
import com.op.gg.ogj.map.model.entity.Map;
import com.op.gg.ogj.map.repository.MapJpaRepository;
import com.op.gg.ogj.map.valid.MapValid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MapValidService {

    public final MapJpaRepository mapJpaRepository;

    public void createMapValid(MapParam mapParam){
        GameValid.GAME_ID_LOCK.validLogic(mapParam.getGameId());
        MapValid.MAP_MAP_NM_LOCK.validLogic(mapParam.getMapNm());
        Map dbMap = mapJpaRepository.findMapByMapNmAndGame_GameId(mapParam.getMapNm(), mapParam.getGameId());
        MapValid.MAP_MAP_EXIT.validLogic(dbMap);
    }

    public void updateMapValid(MapParam mapParam) {
        GameValid.GAME_ID_LOCK.validLogic(mapParam.getGameId());
        MapValid.MAP_MAP_ID_LOCK.validLogic(mapParam.getMapId());
        MapValid.MAP_MAP_NM_LOCK.validLogic(mapParam.getMapNm());
        Map dbMap = mapJpaRepository.findMapByGame_GameIdAndMapIdAndMapNm(mapParam.getGameId(), mapParam.getMapId(), mapParam.getMapNm());
        MapValid.MAP_MAP_EXIT.validLogic(dbMap);
    }

    public void detailMap(MapSearch mapSearch) {
        MapValid.MAP_MAP_ID_LOCK.validLogic(mapSearch.getGameId());
    }

    public void delMapValid(MapParam mapParam) { MapValid.MAP_MAP_ID_LOCK.validLogic(mapParam.getMapId()); }

    public void delMapsValid(MapParam mapParam) { MapValid.MAP_MAPS_ID_LOCK.validLogic(mapParam.getMapsId()); }

    public void pageMapValid(MapSearch mapSearch) { CharacterValid.CHARACTER_ID_LOCK.validLogic(mapSearch.getGameId()); }
}
