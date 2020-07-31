package com.op.gg.ogj.game.rts.model;

import com.op.gg.ogj.game.model.DeviceType;
import com.op.gg.ogj.game.model.Game;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Entity
@DiscriminatorValue(value = "RTS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Rts extends Game {

    private Boolean licenseYn;

    protected Rts(String gameNm, Integer price, String brand, DeviceType deviceType, Boolean actYn){
        super(gameNm, price, brand, deviceType, actYn);
    }

    public static Rts createRts(String gameNm, Integer price, String brand, DeviceType deviceType, Boolean actYn, Boolean licenseYn){
        return new Rts(gameNm, price, brand, deviceType, actYn).rtsCreateDefault(licenseYn);
    }

    public Rts rtsCreateDefault(Boolean licenseYn){
        this.licenseYn = licenseYn;
        return this;
    }

}
