package com.op.gg.ogj.game.service;

import com.op.gg.ogj.game.model.DeviceType;
import com.op.gg.ogj.game.model.GameFactoryMethod;
import com.op.gg.ogj.game.model.GameParam;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class GameServiceTest {

    @Autowired
    GameService gameService;

    @Test
    @Transactional
    @Commit
    public void createGameTest(){
        //given
        GameParam gameParam = GameParam.builder()
                .gameNm("game1")
                .price(5000)
                .brand("intell")
                .deviceType(DeviceType.PC)
                .gameType(GameFactoryMethod.FPS)
                .storyYn(true)
                .gameInfo1("test1")
                .gameInfo2("test2").build();

        //when
        gameService.createGame(gameParam);
    }

}
