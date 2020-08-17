package com.op.gg.ogj.map.model.entity;

import com.google.common.collect.Lists;
import com.op.gg.ogj.config.baseDate.BaseDate;
import com.op.gg.ogj.game.model.entity.Game;
import com.op.gg.ogj.ost.model.entity.Ost;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@Table(name = "map")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Map extends BaseDate {

    @Id @GeneratedValue
    private Long mapId;

    private String mapNm;

    private Boolean actYn;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "game_id")
    private Game game;

    @OneToMany(mappedBy = "map")
    private List<Ost> osts = Lists.newArrayList();

    protected Map(String mapNm){
        this.mapNm = mapNm;
    }

    public static Map createMap(String mapNm){
        return new Map(mapNm).mapCreateDefault();
    }

    public void updateMap(String mapNm) { this.mapNm = mapNm; }

    public Map mapCreateDefault(){
        this.actYn = true;
        return this;
    }

    public void smOstAdd(Ost ost){
        ostAdd(ost);
        ost.mapChange(this);
    }

    public void smGameChange(Game game){
        gameChage(game);
        game.mapAdd(this);
    }

    public void gameChage(Game game) { this.game = game;}

    public void ostAdd(Ost ost){
        this.osts.add(ost);
    }


}
