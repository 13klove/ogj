package com.op.gg.ogj.game.service;

import com.op.gg.ogj.game.model.DeviceType;
import com.op.gg.ogj.game.model.GameFactoryMethod;
import com.op.gg.ogj.game.model.dto.GameParam;
import com.op.gg.ogj.game.model.dto.GameResponse;
import com.op.gg.ogj.game.model.dto.GameSearch;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Slf4j
@SpringBootTest
public class GameServiceTest {

    @Autowired
    GameService gameService;

    @Test
    @Transactional
    public void createGameTest(){
        //given
        GameParam gameParam = GameParam.builder()
                .gameNm("game133")
                .price(5000)
                .brand("intell")
                .deviceType(DeviceType.PC)
                .gameType(GameFactoryMethod.FPS)
                .storyYn(true)
                .gameInfo1("test1")
                .gameInfo2("test2").build();

        //when
        Long id = gameService.createGame(gameParam);

        //then
        Assertions.assertNotNull(id);
    }

    @Test
    @Transactional
    public void updateGameTest(){
        //given
        GameParam gameParam = GameParam.builder()
                .gameId(5l)
                .gameNm("game13")
                .price(5000)
                .brand("core")
                .deviceType(DeviceType.PC)
                .gameType(GameFactoryMethod.FPS)
                .storyYn(true)
                .gameInfo1("test1")
                .build();

        //when
        Long id = gameService.updateGame(gameParam);

        //then
        Assertions.assertNotNull(id);
    }

    @Test
    @Transactional
    public void pageGameTest(){
        //given
        GameSearch gameSearch = GameSearch.builder().startDate(LocalDate.now().plusDays(-5)).endDate(LocalDate.now().plusDays(1)).build();

        //when
        Page<GameResponse> page = gameService.pageGame(gameSearch, PageRequest.of(0, 10));
        for(GameResponse temp : page){
            System.out.println(temp);
        }

        //then
        Assertions.assertNotNull(page);
    }

    @Test
    @Transactional
    public void detailGameTest(){
        //given
        GameSearch gameSearch = GameSearch.builder().gameId(13l).build();

        //when
        GameResponse game = gameService.detailGame(gameSearch);
        System.out.println(game);

        //then
        Assertions.assertNotNull(game);
    }



}
