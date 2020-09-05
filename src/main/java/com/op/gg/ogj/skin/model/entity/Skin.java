package com.op.gg.ogj.skin.model.entity;

import com.op.gg.ogj.config.baseDate.BaseDate;
import com.op.gg.ogj.character.model.entity.Character;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "skin")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Skin extends BaseDate {

    @Id @GeneratedValue
    private Long skinId;

    private String skinNm;

    private Long price;

    private Boolean actYn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "character_id")
    private Character character;

    protected Skin(String skinNm, Long price){
        this.skinNm = skinNm;
        this.price = price;
    }

    public static Skin createSkin(String skinNm, Long price){
        return new Skin(skinNm, price).skinCreateDefault();
    }

    public Skin skinCreateDefault(){
        this.actYn = true;
        return this;
    }

    public void smCharacterChange(Character character){
        characterChange(character);
        character.skinAdd(this);
    }

    public void characterChange(Character character){
        this.character = character;
    }

    public void delSkin() { this.actYn = false; }

}
