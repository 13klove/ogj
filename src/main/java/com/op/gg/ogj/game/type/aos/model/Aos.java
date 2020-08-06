package com.op.gg.ogj.game.type.aos.model;

import com.op.gg.ogj.game.model.DeviceType;
import com.op.gg.ogj.game.model.Game;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Entity
@DiscriminatorValue(value = "AOS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Aos extends Game {

    private Boolean usemapYn;

    protected Aos(String gameNm, Integer price, String brand, DeviceType deviceType){
        super(gameNm, price, brand, deviceType);
    }

    public static Aos createAos(String gameNm, Integer price, String brand, DeviceType deviceType, Boolean usemapYn){
        return new Aos(gameNm, price, brand, deviceType).aosCreateDefault(usemapYn);
    }

    public Aos aosCreateDefault(Boolean usemapYn){
        this.usemapYn = usemapYn;
        return this;
    }

}
