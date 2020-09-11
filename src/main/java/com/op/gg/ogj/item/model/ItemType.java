package com.op.gg.ogj.item.model;

public enum ItemType {

    ATTACK("무기"),
    ARMOR("보호구");

    private String desc;

    ItemType(String desc){
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

}
