package com.op.gg.ogj.game.model;

import com.google.common.collect.Lists;
import com.op.gg.ogj.character.model.Character;
import com.op.gg.ogj.config.baseDate.BaseDate;
import com.op.gg.ogj.gameInfo.model.GameInfo;
import com.op.gg.ogj.ost.model.Ost;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@Table(name = "game")
@DiscriminatorColumn(name = "game_type")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Game extends BaseDate {

    @Id
    @GeneratedValue
    private Long gameId;

    private String gameNm;

    private Integer price;

    private String brand;

    @Enumerated(EnumType.STRING)
    private DeviceType deviceType;

    private Boolean actYn;

    @OneToOne(mappedBy = "game", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    private GameInfo gameInfo;

    @OneToMany(mappedBy = "game", cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    private List<Ost> osts = Lists.newArrayList();

    @OneToMany(mappedBy = "game", cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    private List<Character> characters = Lists.newArrayList();

    protected Game(String gameNm, Integer price, String brand, DeviceType deviceType, Boolean actYn) {
        this.gameNm = gameNm;
        this.price = price;
        this.brand = brand;
        this.deviceType = deviceType;
        this.actYn = actYn;
    }

    public void smOstAdd(Ost ost){
        ostAdd(ost);
        ost.gameChange(this);
    }

    public void smGameInfoChange(GameInfo gameInfo){
        gameInfoChange(gameInfo);
        gameInfo.gameChange(this);
    }

    public void smCharacterAdd(Character character){
        characterAdd(character);
        character.gameChange(this);
    }

    public void gameInfoChange(GameInfo gameInfo) {
        this.gameInfo = gameInfo;
    }

    public void ostAdd(Ost ost) {
        this.osts.add(ost);
    }

    public void characterAdd(Character character) {
        this.characters.add(character);
    }
}
