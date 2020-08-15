package com.op.gg.ogj.game.controller;

import com.op.gg.ogj.game.model.dto.GameParam;
import com.op.gg.ogj.game.service.GameService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"game"})
@RestController
@RequestMapping("/api/game")
@RequiredArgsConstructor
public class GameContoller {

    private final GameService gameService;
//https://eblo.tistory.com/48

    @PostMapping
    @ApiOperation(value = "게임 등록", notes = "게임 생성")
    public ResponseEntity<GameParam> createGame(@RequestBody GameParam gameParam, BindingResult bindingResult){
        return new ResponseEntity<GameParam>(GameParam.builder().gameNm("abc").build(), HttpStatus.OK);
        //gameService.gameValid(bindingResult);
        //return gameService.createGame(gameParam);
    }

}