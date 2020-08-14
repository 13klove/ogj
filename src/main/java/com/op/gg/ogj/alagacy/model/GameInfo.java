//package com.op.gg.ogj.alagacy.model;
//
//import com.op.gg.ogj.config.baseDate.BaseDate;
//import com.op.gg.ogj.game.model.entity.Game;
//import lombok.AccessLevel;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.ToString;
//import org.hibernate.annotations.DynamicUpdate;
//import org.springframework.data.domain.Persistable;
//
//import javax.persistence.*;
//
//@Getter
//@Entity
//@DynamicUpdate
//@Table(name = "game_info")
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@ToString(of = {"gameId", "gameInfo1", "gameInfo2", "actYn", "createDt", "updateDt", "updateId"})
//public class GameInfo extends BaseDate implements Persistable<Long> {
//
//    @Id
//    private Long gameId;
//
//    private String gameInfo1;
//
//    private String gameInfo2;
//
//    private Boolean actYn;
//
//    @MapsId
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "game_id")
//    private Game game;
//
//    @Override
//    public Long getId() {
//        return gameId;
//    }
//
//    @Override
//    public boolean isNew() {
//        return getCreateDt() == null;
//    }
//
//    protected GameInfo(String gameInfo1, String gameInfo2){
//        this.gameInfo1 = gameInfo1;
//        this.gameInfo2 = gameInfo2;
//    }
//
//    public GameInfo gameInfoCreateDefault(){
//        this.actYn = true;
//        return this;
//    }
//
//    public static GameInfo createGameInfo(String gameInfo1, String gameInfo2){
//        return new GameInfo(gameInfo1, gameInfo2).gameInfoCreateDefault();
//    }
//
//    public void gameInfoUpdate(String gameInfo1, String gameInfo2) {
//        this.gameInfo1 = gameInfo1;
//        this.gameInfo2 = gameInfo2;
//    }
//
//    public void smGameChange(Game game){
//        gameChange(game);
//        game.gameInfoChange(this);
//    }
//
//    public void gameChange(Game game) {
//        this.game = game;
//        this.gameId = game.getGameId();
//    }
//
//}
