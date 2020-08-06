package com.op.gg.ogj.game.type.fps.repository;

import com.op.gg.ogj.game.model.DeviceType;
import com.op.gg.ogj.game.type.fps.model.Fps;
import com.op.gg.ogj.gameInfo.model.GameInfo;
import com.op.gg.ogj.gameInfo.repository.GameInfoJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class FpsJpaRepositoryTest {

    @Autowired
    FpsJpaRepository fpsJpaRepository;

    @Autowired
    GameInfoJpaRepository gameInfoJpaRepository;

    @Test
    @Transactional
    public void save_fps_test(){
        Fps fps = Fps.createFps("a", 500, "brand", DeviceType.PC, true);
        fpsJpaRepository.save(fps);
    }

    @Test
    @Transactional
    public void save_fps_gameInfo_test(){
        Fps fps = Fps.createFps("a", 500, "brand", DeviceType.PC, true);
        fpsJpaRepository.save(fps);
        GameInfo gi = GameInfo.createGameInfo("1", "2");
        System.out.println(fps.getGameId());
        gi.smGameChange(fps);
        gameInfoJpaRepository.save(gi);
    }

}
