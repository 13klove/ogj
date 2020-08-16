package com.op.gg.ogj.map.repository;

import com.op.gg.ogj.map.model.entity.Map;
import com.op.gg.ogj.map.repository.queryDsl.dto.MapDtoDslRepository;
import com.op.gg.ogj.map.repository.queryDsl.entity.MapDslRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MapJpaRepository extends JpaRepository<Map, Long>, MapDslRepository, MapDtoDslRepository {

    Map findMapByMapNmAndGame_GameId(String mapNm, Long gameId);

    Map findMapByGame_GameIdAndMapIdAndMapNm(Long gameId, Long mapId, String mapNm);

    List<Map> findMapsByMapIdIn(List<Long> mapId);

}
