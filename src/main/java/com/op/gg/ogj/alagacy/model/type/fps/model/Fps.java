package com.op.gg.ogj.alagacy.model.type.fps.model;

import com.op.gg.ogj.game.model.DeviceType;
import com.op.gg.ogj.game.model.entity.Game;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

//@Getter
//@Entity
//@DiscriminatorValue(value = "FPS")
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
////@ToString(of = {"gameId", "gameNm", "gameType", "price", "brand", "deviceType", "storyYn"})
//public class Fps extends Game {
//
//    private Boolean storyYn;
//
//    protected Fps(String gameNm, Integer price, String brand, DeviceType deviceType){
//        super(gameNm, price, brand, deviceType);
//    }
//
//    public static Fps createFps(String gameNm, Integer price, String brand, DeviceType deviceType, Boolean storyYn){
//        return new Fps(gameNm, price, brand, deviceType).fpsCreateDefault(storyYn);
//    }
//
//    public void updateFps(Boolean storyYn){
//        this.storyYn = storyYn;
//    }
//
//    public Fps fpsCreateDefault(Boolean storyYn){
//        this.storyYn = true;
//        return this;
//    }
//
//}
