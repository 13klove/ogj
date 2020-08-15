package com.op.gg.ogj.game.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class GameJpaRepositoryTest {

    @Autowired
    GameJpaRepository gameJpaRepository;

    @Test
    @Transactional
    public void save_game_test(){

//        Game game = gameJpaRepository.findGameByGameId(2l);
//        System.out.println(game);
//        System.out.println(game.getGameInfo());

    }

}
