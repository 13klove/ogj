package com.op.gg.ogj.game.valied;

public enum GameValid {

    GAME_ID_LOCK("게임ID가 없습니다."),
    GAME_LOCK("게임이 존재 하지 않습니다.");

    private String desc;

    GameValid(String desc){
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

}
