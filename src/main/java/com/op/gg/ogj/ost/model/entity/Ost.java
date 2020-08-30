package com.op.gg.ogj.ost.model.entity;

import com.op.gg.ogj.config.baseDate.BaseDate;
import com.op.gg.ogj.game.model.entity.Game;
import com.op.gg.ogj.map.model.entity.Map;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "ost")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Ost extends BaseDate {

    @Id
    @GeneratedValue
    private Long ostId;

    private String ostNm;

    private Boolean actYn;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "game_id")
    private Game game;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "map_id")
    private Map map;

    public Ost(String ostNm){
        this.ostNm = ostNm;
    }

    public static Ost createOst(String ostNm){
        Ost ost = new Ost(ostNm);
        return ost.ostCreateDefault();
    }

    public void updateOst(String ostNm){
        this.ostNm = ostNm;
    }

    public Ost ostCreateDefault(){
        this.actYn = true;
        return this;
    }

    public void gameChange(Game game) {
        this.game = game;
    }

    public void smGmaeChange(Game game){
        gameChange(game);
        game.ostAdd(this);
    }

    public void smMapChange(Map map){
        mapChange(map);
        map.ostAdd(this);
    }

    public void mapChange(Map map) {
        this.map = map;
    }

    public void delOst(){this.actYn=false;}

    public void delMap(){this.map=null;}
}
