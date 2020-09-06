package com.op.gg.ogj.skin.controller;

import com.op.gg.ogj.config.domain.response.ResponseDto;
import com.op.gg.ogj.skin.model.dto.SkinParam;
import com.op.gg.ogj.skin.model.dto.SkinSearch;
import com.op.gg.ogj.skin.service.SkinService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = {"skin"})
@RequiredArgsConstructor
@RequestMapping("/api/skin")
public class SkinController {


    private final SkinService skinService;

    @PostMapping
    @ApiOperation(notes = "스킨등록", value = "스킨등록")
    public ResponseDto createSkin(@RequestBody SkinParam skinParam){
        return ResponseDto.of(skinService.createSkin(skinParam));
    }

    @PutMapping("{id}")
    @ApiOperation(notes = "스킨수정", value = "스킨수정")
    public ResponseDto updateSkin(@PathVariable("id") Long skinId, @RequestBody SkinParam skinParam){
        skinParam.setSkinId(skinId);
        return ResponseDto.of(skinService.updateSkin(skinParam));
    }

    @GetMapping
    @ApiOperation(notes = "스킨리스트", value = "스킨리스트")
    public ResponseDto pageSkin(SkinSearch skinSearch, Pageable pageable){
        return ResponseDto.of(skinService.pageSkin(skinSearch, pageable));
    }

    @GetMapping("{id}")
    @ApiOperation(notes = "스킨상세", value = "스킨상세")
    public ResponseDto detailSkin(@PathVariable("id") Long skinId, SkinSearch skinSearch){
        skinSearch.setSkinId(skinId);
        return ResponseDto.of(skinService.detailSkin(skinSearch));
    }

    @PutMapping("{id}/del")
    @ApiOperation(notes = "스킨삭제", value = "스킨삭제")
    public void delSkin(@PathVariable("id") Long skinId){
        SkinParam skinParam = SkinParam.builder().skinId(skinId).build();
        skinService.delSkin(skinParam);
    }

    @PostMapping("dels")
    @ApiOperation(notes = "스킨 복수 삭제", value = "스킨 복수 삭제")
    public void delSkins(@RequestBody SkinParam skinParam){
        skinService.delSkins(skinParam);
    }

}
