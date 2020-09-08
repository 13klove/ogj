package com.op.gg.ogj.item.controller;

import com.op.gg.ogj.config.domain.response.ResponseDto;
import com.op.gg.ogj.item.model.dto.ItemParam;
import com.op.gg.ogj.item.model.dto.ItemSearch;
import com.op.gg.ogj.item.service.ItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "item")
@RequiredArgsConstructor
@RequestMapping("/api/item")
public class ItemController {

    private final ItemService itemService;

    @PostMapping
    @ApiOperation(notes = "아이템 등록", value = "아이템 등록")
    public ResponseDto createItem(@RequestBody ItemParam itemParam){
        return ResponseDto.of(itemService.createItem(itemParam));
    }

    @PutMapping("{id}")
    @ApiOperation(notes = "아이템 수정", value = "아이템 수정")
    public ResponseDto updateItem(@PathVariable("id") Long itemId, @RequestBody ItemParam itemParam){
        itemParam.setItemId(itemId);
        return ResponseDto.of(itemService.updateItem(itemParam));
    }

    @GetMapping
    @ApiOperation(notes = "페이지 아이템", value = "페이지 아이템")
    public ResponseDto pageItem(ItemSearch itemSearch, Pageable pageable){
        return ResponseDto.of(itemService.pageItem(itemSearch, pageable));
    }

    @GetMapping("{id}")
    @ApiOperation(notes = "아이템 상세", value = "아이템 상세")
    public ResponseDto detailItem(ItemSearch itemSearch){
        return ResponseDto.of(itemService.detailItem(itemSearch));
    }

    @PutMapping("{id}/del")
    @ApiOperation(notes = "아이템 삭제", value = "아이템 삭제")
    public void delItem(@PathVariable("id") Long itemId, ItemParam itemParam){
        itemParam.setItemId(itemId);
        itemService.delItem(itemParam);
    }

    @PostMapping("dels")
    @ApiOperation(notes = "아이템 복수 삭제", value = "아이템 복수 삭제")
    public void delItems(ItemParam itemParam){
        itemService.delItems(itemParam);
    }

}
