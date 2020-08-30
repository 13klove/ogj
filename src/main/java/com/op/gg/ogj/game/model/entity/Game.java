package com.op.gg.ogj.game.model.entity;

import com.google.common.collect.Lists;
import com.op.gg.ogj.config.baseDate.BaseDate;
import com.op.gg.ogj.game.model.DeviceType;
import com.op.gg.ogj.game.model.GameFactoryMethod;
import com.op.gg.ogj.gameInfo.model.entity.GameInfo;
import com.op.gg.ogj.map.model.entity.Map;
import com.op.gg.ogj.character.model.entity.Character;
import com.op.gg.ogj.ost.model.entity.Ost;
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

    @Enumerated(EnumType.STRING)
    @Column(name = "game_type", insertable = false, updatable = false)
    private GameFactoryMethod gameType;

    private Boolean actYn;

    @JoinColumn(name = "game_info_id")
    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    private GameInfo gameInfo;

    @OneToMany(mappedBy = "game", cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    private List<Ost> osts = Lists.newArrayList();

    @OneToMany(mappedBy = "game", cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    private List<Character> characters = Lists.newArrayList();

    @OneToMany(mappedBy = "game", cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    private List<Map> maps = Lists.newArrayList();

    protected Game(String gameNm, Integer price, String brand, DeviceType deviceType) {
        this.gameNm = gameNm;
        this.price = price;
        this.brand = brand;
        this.deviceType = deviceType;
        this.actYn = true;
    }

    public static Game createGame(String gameNm, Integer price, String brand, DeviceType deviceType, Boolean actYn) {
        return new Game(gameNm, price, brand, deviceType).gameCreateDefault();
    }

    public void gameUpdate(String gameNm, Integer price, String brand, DeviceType deviceType){
        this.gameNm = gameNm;
        this.price = price;
        this.brand = brand;
        this.deviceType = deviceType;
    }

    public Game gameCreateDefault(){
        this.actYn = true;
        return this;
    }

    public void smMapAdd(Map map){
        mapAdd(map);
        map.gameChage(this);
    }

    public void smMapsAdd(List<Map> maps){
        maps.forEach(this::smMapAdd);
    }

    public void smOstAdd(Ost ost){
        ostAdd(ost);
        ost.gameChange(this);
    }

    public void smCharacterAdd(Character character){
        characterAdd(character);
        character.gameChange(this);
    }

    public void mapAdd(Map map) { this.maps.add(map); }

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
