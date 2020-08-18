package com.op.gg.ogj.map.service;

import com.op.gg.ogj.map.model.dto.MapParam;
import com.op.gg.ogj.map.model.dto.MapResponse;
import com.op.gg.ogj.map.model.dto.MapSearch;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Slf4j
@SpringBootTest
public class MapServiceTest {

    @Autowired
    private MapService mapService;

    @Test
    @Transactional
    public void createMapTest(){
        //given
        MapParam mp = MapParam.builder().gameId(7l).mapNm("헌터x").build();

        //when
        Long mapId = mapService.createMap(mp);

        //then eyecheck
        log.info("mapId: {}", mapId);
        Assertions.assertNotNull(mapId);
    }

    @Test
    @Transactional
    public void updateMapTest(){
        //given
        MapParam mp = MapParam.builder().gameId(7l).mapId(17l).mapNm("블루스톰").build();

        //when
        Long mapId = mapService.updateMap(mp);

        //then eye check
        log.info("mpaId: {}", mapId);
        Assertions.assertNotNull(mapId);
    }

    @Test
    @Transactional
    public void delMapTest(){
        //given
        MapParam mp = MapParam.builder().mapId(17l).build();

        //when
        mapService.delMap(mp);

        //then
    }

    @Test
    @Transactional
    public void delMapsTest(){
        //given
        MapParam mp = MapParam.builder().mapsId(Arrays.asList(18l, 19l)).build();

        //when
        mapService.delMap(mp);

        //then
    }

    @Test
    @Transactional
    public void pageMapTest(){
        //given
        MapSearch ms = MapSearch.builder().gameId(7l).build();
        //when
        Page<MapResponse> page = mapService.pageMap(ms, PageRequest.of(0, 10));

        //then eye Check
        page.forEach(a->log.info("{}", a));
        Assertions.assertNotNull(page);
    }

    @Test
    @Transactional
    public void detailMapTest(){
        //given
        MapSearch ms = MapSearch.builder().mapId(20l).build();
        //when
        MapResponse map = mapService.detailMap(ms);
        //then eye check
        log.info("{}", map);
        Assertions.assertNotNull(map);
    }

}
