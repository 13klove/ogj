package com.op.gg.ogj.skill.model;

public enum SkillType {

//    ULTIMATE("궁극기"),
//    SKILL("기술");
    HEAL("치유"),
    ATTACK("가격");

    private String desc;

    SkillType(String desc){
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

}
