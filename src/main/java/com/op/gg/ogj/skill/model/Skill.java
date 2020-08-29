package com.op.gg.ogj.skill.model;

import com.op.gg.ogj.config.baseDate.BaseDate;
import com.op.gg.ogj.character.model.entity.Character;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "skill")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Skill extends BaseDate {

    @Id
    @GeneratedValue
    private Long sillId;

    private String skillNm;

    private Integer useEnergy;

    private Integer useLife;

    private Integer damage;

    @Enumerated(EnumType.STRING)
    private SkillType skillType;

    private Boolean ulimateYn;

    private String imgUrl;

    private Boolean actYn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "character_id")
    private Character character;

    protected Skill(String skillNm, Integer useEnergy, Integer useLife, Integer damage, SkillType skillType, Boolean ulimateYn, String imgUrl){
        this.skillNm = skillNm;
        this.useEnergy = useEnergy;
        this.useLife = useLife;
        this.damage = damage;
        this.skillType = skillType;
        this.ulimateYn = ulimateYn;
        this.imgUrl = imgUrl;
    }

    public static Skill createSkill(String skillNm, Integer useEnergy, Integer useLife, Integer damage, SkillType skillType, Boolean ulimateYn, String imgUrl){
        return new Skill(skillNm, useEnergy, useLife, damage, skillType, ulimateYn, imgUrl).skillCreateDefault();
    }

    public Skill skillCreateDefault(){
        this.actYn = true;
        return this;
    }

    public void smCharacterChange(Character character){
        characterChange(character);
        character.skillAdd(this);
    }

    public void characterChange(Character character){
        this.character = character;
    }

}
