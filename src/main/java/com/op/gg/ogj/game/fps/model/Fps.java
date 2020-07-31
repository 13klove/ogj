package com.op.gg.ogj.game.fps.model;

import com.op.gg.ogj.game.model.DeviceType;
import com.op.gg.ogj.game.model.Game;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Entity
@DiscriminatorValue(value = "FPS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@ToString(of = {"gameId", "gameNm", "gameType", "price", "brand", "deviceType", "storyYn"})
public class Fps extends Game {

    private Boolean storyYn;

    protected Fps(String gameNm, Integer price, String brand, DeviceType deviceType, Boolean actYn){
        super(gameNm, price, brand, deviceType, actYn);
    }

    public static Fps createFps(String gameNm, Integer price, String brand, DeviceType deviceType, Boolean storyYn, Boolean actYn){
        return new Fps(gameNm, price, brand, deviceType, actYn).fpsCreateDefault(storyYn);
    }

    public Fps fpsCreateDefault(Boolean storyYn){
        this.storyYn = true;
        return this;
    }

}
