package com.op.gg.ogj.ost.model;

import com.op.gg.ogj.config.baseDate.BaseDate;
import com.op.gg.ogj.game.model.Game;
import com.op.gg.ogj.map.model.Map;
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
}
