package com.op.gg.ogj.map.controller;

import com.op.gg.ogj.config.domain.response.ResponseDto;
import com.op.gg.ogj.map.model.dto.MapParam;
import com.op.gg.ogj.map.model.dto.MapSearch;
import com.op.gg.ogj.map.service.MapService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"map"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/map")
public class MapController {

    private final MapService mapService;

    @PostMapping
    @ApiOperation(value = "맵 등록", notes = "맵 등록")
    public ResponseDto createMap(@RequestBody MapParam mapParam){
        return ResponseDto.of(mapService.createMap(mapParam));
    }

    @PutMapping("{id}")
    @ApiOperation(value = "맵 수정", notes = "맵 수정")
    public ResponseDto updateMap(@RequestBody MapParam mapParam, @PathVariable("id") Long mapId){
        mapParam.setMapId(mapId);
        return ResponseDto.of(mapService.updateMap(mapParam));
    }

    @GetMapping
    @ApiOperation(value = "맵 조회(페이지)", notes = "맵 조회(페이지)")
    public ResponseDto pageMap(MapSearch mapSearch, Pageable pageable){
        return ResponseDto.of(mapService.pageMap(mapSearch, pageable));
    }

    @GetMapping("{id}")
    @ApiOperation(value = "맵 상세 조회", notes = "맵 상세 조회")
    public ResponseDto detailMap(MapSearch mapSearch, @PathVariable("id") Long mapId){
        mapSearch.setMapId(mapId);
        return ResponseDto.of(mapService.detailMap(mapSearch));
    }

    @PutMapping("del/{id}")
    @ApiOperation(value = "맵 삭제", notes = "맵 삭제")
    public void delMap(@PathVariable("id") Long mapId){
        mapService.delMap(MapParam.builder().mapId(mapId).build());
    }

    @PostMapping("del")
    @ApiOperation(value = "다중 맵 삭제", notes = "다중 맵 삭제")
    public void delMaps(@RequestBody MapParam mapParam){
        mapService.delMaps(mapParam);
    }

}
