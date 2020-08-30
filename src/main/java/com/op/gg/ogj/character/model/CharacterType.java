package com.op.gg.ogj.character.model;

public enum CharacterType {

    DEAL("딜러"),
    HEAL("힐러"),
    TANK("텡커"),

    WORKMAN("일꾼"),
    ATTACK("공격형"),
    MAGIC("마법형");

    private String desc;

    CharacterType(String desc){
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

}
