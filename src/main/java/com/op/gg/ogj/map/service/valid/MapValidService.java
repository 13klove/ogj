package com.op.gg.ogj.map.service.valid;

import com.op.gg.ogj.config.exception.domain.ParamValidException;
import com.op.gg.ogj.map.model.dto.MapParam;
import com.op.gg.ogj.map.model.dto.MapSearch;
import com.op.gg.ogj.map.model.entity.Map;
import com.op.gg.ogj.map.repository.MapJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MapValidService {

    public final MapJpaRepository mapJpaRepository;

    public void createMapValid(MapParam mapParam){
        if(mapParam.getGameId() == null) throw new ParamValidException("게임에 이상이 있습니다.");
        Map dbMap = mapJpaRepository.findMapByMapNmAndGame_GameId(mapParam.getMapNm(), mapParam.getGameId());
        if(dbMap != null) throw new ParamValidException("이미 등록된 맵입니다.");
    }

    public void updateMapValid(MapParam mapParam) {
        if(mapParam.getGameId() == null) throw new ParamValidException("게임에 이상이 있습니다.");
        if(mapParam.getMapId() == null) throw new ParamValidException("맵에 이상이 있습니다.");
        Map dbMap = mapJpaRepository.findMapByGame_GameIdAndMapIdAndMapNm(mapParam.getGameId(), mapParam.getMapId(), mapParam.getMapNm());
        if(dbMap != null) throw new ParamValidException("이미 등록된 맵입니다.");
    }


    public void delMapValid(MapParam mapParam) {
        if(mapParam.getMapId() == null && mapParam.getMapsId().isEmpty()) throw new ParamValidException("삭제할 맵을 선택하세요");
    }

    public void detailMap(MapSearch mapSearch) {
        if(mapSearch.getMapId() == null) throw new ParamValidException("맵에 이상이 있습니다.");
    }
}
