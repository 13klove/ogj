package com.op.gg.ogj.character.controller;

import com.op.gg.ogj.character.model.dto.CharacterParam;
import com.op.gg.ogj.character.model.dto.CharacterSearch;
import com.op.gg.ogj.character.service.CharacterService;
import com.op.gg.ogj.config.domain.response.ResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Api(tags = {"character"})
@RequestMapping("/api/character")
public class CharacterController {

    private final CharacterService characterService;

    @PostMapping
    @ApiOperation(value = "캐릭터 등록", notes = "캐릭터 등록")
    public ResponseDto createCharacter(@RequestBody CharacterParam characterParam){
        return ResponseDto.of(characterService.createCharacter(characterParam));
    }

    @PutMapping("{id}")
    @ApiOperation(value = "캐릭터 수정", notes = "캐릭터 수정")
    public ResponseDto updateCharacter(@PathVariable("id") Long characterId, @RequestBody CharacterParam characterParam){
        characterParam.setCharacterId(characterId);
        return ResponseDto.of(characterService.updateCharacter(characterParam));
    }

    @GetMapping
    @ApiOperation(value = "캐릭터 페이지", notes = "캐릭터 페이지")
    public ResponseDto pageCharacter(CharacterSearch characterSearch, Pageable pageable){
        return ResponseDto.of(characterService.pageCharacter(characterSearch, pageable));
    }

    @GetMapping("{id}")
    @ApiOperation(value = "캐릭터 상세", notes = "캐릭터 상세")
    public ResponseDto detailCharacter(@PathVariable("id") Long characterId, CharacterSearch characterSearch){
        characterSearch.setCharacterId(characterId);
        return ResponseDto.of(characterService.detailCharacter(characterSearch));
    }

    @PutMapping("{id}/del")
    @ApiOperation(value = "캐릭터 삭제", notes = "캐릭터 삭제")
    public void delCharacter(@PathVariable("id") Long characterId, @RequestBody CharacterParam characterParam){
        characterParam.setCharacterId(characterId);
        characterService.delCharacter(characterParam);
    }

    @PostMapping("del")
    @ApiOperation(value = "캐릭터 일괄 삭제", notes = "캐릭터 일괄 삭제")
    public void delCharacters(@RequestBody CharacterParam characterParam){
        characterService.delCharacters(characterParam);
    }


}
