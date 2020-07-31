package com.op.gg.ogj.game.rpg.model;

import com.op.gg.ogj.game.model.DeviceType;
import com.op.gg.ogj.game.model.Game;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Entity
@DiscriminatorValue(value = "RPG")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Rpg extends Game {

    private Boolean autoPlayYn;

    protected Rpg(String gameNm, Integer price, String brand, DeviceType deviceType, Boolean actYn){
        super(gameNm, price, brand, deviceType, actYn);
    }

    public static Rpg createRpg(String gameNm, Integer price, String brand, DeviceType deviceType, Boolean actYn, Boolean autoPlayYn){
        return new Rpg(gameNm, price, brand, deviceType, actYn).rpgCreateDefault(autoPlayYn);
    }

    public Rpg rpgCreateDefault(Boolean autoPlayYn){
        this.autoPlayYn = autoPlayYn;
        return this;
    }

}
