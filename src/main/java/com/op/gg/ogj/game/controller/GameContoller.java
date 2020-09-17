package com.op.gg.ogj.game.controller;

import com.op.gg.ogj.config.domain.response.ResponseDto;
import com.op.gg.ogj.game.model.dto.GameParam;
import com.op.gg.ogj.game.model.dto.GameSearch;
import com.op.gg.ogj.game.service.GameService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

////https://eblo.tistory.com/48

@Api(tags = {"game"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/game")
public class GameContoller {

    private final GameService gameService;

    @PostMapping
    @ApiOperation(value = "게임 등록", notes = "게임 생성")
    public ResponseDto createGame(@RequestBody GameParam gameParam){
        System.out.println(gameParam);
        return ResponseDto.of(gameService.createGame(gameParam));
    }

    @PutMapping("{id}")
    @ApiOperation(value = "게임 수정", notes = "게임 수정")
    public ResponseDto updateGame(@PathVariable(value = "id") Long gameId, @RequestBody GameParam gameParam){
        gameParam.setGameId(gameId);
        return ResponseDto.of(gameService.updateGame(gameParam));
    }

    @GetMapping
    @ApiOperation(value = "게임 조회(페이지)", notes = "게임 조회(페이지")
    public ResponseDto pageGame(GameSearch gameSearch, Pageable pageable){
        return ResponseDto.of(gameService.pageGame(gameSearch, pageable));
    }

    @GetMapping("{id}")
    @ApiOperation(value = "게임 상세", notes = "게임 상세")
    public ResponseDto detailGame(@PathVariable(value = "id") Long gameId){
        return ResponseDto.of(gameService.detailGame(GameSearch.builder().gameId(gameId).build()));
    }

    @PutMapping("{id}/del")
    @ApiOperation(value = "게임 삭제", notes = "게임 삭제")
    public void delGame(@PathVariable(value = "id") Long gameId){
        gameService.delGame(GameParam.builder().gameId(gameId).gameIds(Arrays.asList(gameId)).build());
    }

    @PutMapping("dels")
    @ApiOperation(value = "게임 삭제", notes = "게임 삭제")
    public void delGames(@RequestBody GameParam gameParam){
        gameService.delGames(gameParam);
    }

}