package com.op.gg.ogj.game.model;

import com.op.gg.ogj.game.model.dto.GameParam;
import com.op.gg.ogj.game.model.entity.Game;
import com.op.gg.ogj.game.type.aos.model.Aos;
import com.op.gg.ogj.game.type.fps.model.Fps;
import com.op.gg.ogj.game.type.rpg.model.Rpg;
import com.op.gg.ogj.game.type.rts.model.Rts;

public enum GameFactoryMethod {

    FPS{
        protected Game createGame(GameParam gameParam){
            return Fps.createFps(gameParam.getGameNm(), gameParam.getPrice(), gameParam.getBrand(), gameParam.getDeviceType(), gameParam.getStoryYn());
        }
    },
    AOS{
        protected Game createGame(GameParam gameParam){
            return Aos.createAos(gameParam.getGameNm(), gameParam.getPrice(), gameParam.getBrand(), gameParam.getDeviceType(), gameParam.getUsemapYn());
        }
    },
    RPG{
        protected Game createGame(GameParam gameParam){
            return Rpg.createRpg(gameParam.getGameNm(), gameParam.getPrice(), gameParam.getBrand(), gameParam.getDeviceType(), gameParam.getAutoPlayYn());
        }
    },
    RTS{
        protected Game createGame(GameParam gameParam){
            return Rts.createRts(gameParam.getGameNm(), gameParam.getPrice(), gameParam.getBrand(), gameParam.getDeviceType(), gameParam.getLicenseYn());
        }
    }
    ;

//    public Game create(GameParam gameParam){
//        return createGame(gameParam);
//    }

    abstract protected Game createGame(GameParam gameParam);

}
