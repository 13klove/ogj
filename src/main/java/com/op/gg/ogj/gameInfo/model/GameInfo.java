package com.op.gg.ogj.gameInfo.model;

import com.op.gg.ogj.config.baseDate.BaseDate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@Entity
@DynamicUpdate
@Table(name = "game_info")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"gameId", "gameInfo1", "gameInfo2", "actYn", "createDt", "updateDt", "updateId"})
public class GameInfo extends BaseDate {

    @Id
    @GeneratedValue
    private Long gameInfoId;

    private String gameInfo1;

    private String gameInfo2;

    private Boolean actYn;

    protected GameInfo(String gameInfo1, String gameInfo2){
        this.gameInfo1 = gameInfo1;
        this.gameInfo2 = gameInfo2;
    }

    public GameInfo gameInfoCreateDefault(){
        this.actYn = true;
        return this;
    }

    public static GameInfo createGameInfo(String gameInfo1, String gameInfo2){
        return new GameInfo(gameInfo1, gameInfo2).gameInfoCreateDefault();
    }

    public void gameInfoUpdate(String gameInfo1, String gameInfo2) {
        this.gameInfo1 = gameInfo1;
        this.gameInfo2 = gameInfo2;
    }

}
