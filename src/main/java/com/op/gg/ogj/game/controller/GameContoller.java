package com.op.gg.ogj.game.controller;

import com.op.gg.ogj.config.exception.domain.markup.Reg;
import com.op.gg.ogj.game.model.GameParam;
import com.op.gg.ogj.game.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
@RequiredArgsConstructor
public class GameContoller {

    private final GameService gameService;
//https://eblo.tistory.com/48
    @PostMapping
    public ResponseEntity<GameParam> createGame(@Validated(Reg.class) @RequestBody GameParam gameParam, BindingResult bindingResult){
        return new ResponseEntity<GameParam>(GameParam.builder().gameNm("abc").build(), HttpStatus.OK);
        //gameService.gameValid(bindingResult);
        //return gameService.createGame(gameParam);
    }

}
