package com.op.gg.ogj.character.valid;

public enum CharacterValid{

    CHARACTER_ID_LOCK("케릭터ID가 없습니다."),
    CHARACTER_NAME_EXIST("이미 존재하는 케릭터 입니다."),
    CHARACTER_NAME_LOCK("케릭터 이름은 필수 입니다."),
    CHARACTER_TYPE_LOCK("케릭터 타입은 필수 입니다."),
    CHARACTER_LIFE_LOCK("케릭터 생명력은 필수 입니다."),
    CHARACTER_ENERGY_LOCK("케릭터 에어지는 필수 입니다.");

    private String desc;

    CharacterValid(String desc){
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
