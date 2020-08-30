package com.op.gg.ogj.character.model.entity;

import com.google.common.collect.Lists;
import com.op.gg.ogj.character.model.CharacterType;
import com.op.gg.ogj.config.baseDate.BaseDate;
import com.op.gg.ogj.game.model.entity.Game;
import com.op.gg.ogj.item.model.Item;
import com.op.gg.ogj.skill.model.Skill;
import com.op.gg.ogj.skin.model.Skin;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@Table(name = "character")
@ToString(exclude = {"game", "skills", "items"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Character extends BaseDate {

    @Id
    @GeneratedValue
    private Long characterId;

    private String characterNm;

    @Enumerated(EnumType.STRING)
    private CharacterType characterType;

    private Integer life;

    private Integer energy;

    private String imgUrl;

    private Boolean actYn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
    private Game game;

    @OneToMany(mappedBy = "character", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private List<Skill> skills = Lists.newArrayList();

    @OneToMany(mappedBy = "character", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private List<Skin> skins = Lists.newArrayList();

    @OneToMany(mappedBy = "character", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private List<Item> items = Lists.newArrayList();

    protected Character(String characterNm, CharacterType characterType, Integer life, Integer energy, String imgUrl){
        this.characterNm = characterNm;
        this.characterType = characterType;
        this.life = life;
        this.energy = energy;
        this.imgUrl = imgUrl;
    }

    public static Character createCharacter(String characterNm, CharacterType characterType, Integer life, Integer energy, String imgUrl){
        return new Character(characterNm, characterType, life, energy, imgUrl).characterCreateDefault();
    }

    public void updateCharacter(String characterNm, CharacterType characterType, Integer life, Integer energy, String imgUrl) {
        this.characterNm = characterNm;
        this.characterType = characterType;
        this.life = life;
        this.energy = energy;
        this.imgUrl = imgUrl;
    }

    public Character characterCreateDefault(){
        this.actYn = true;
        return this;
    }

    public void smGameChange(Game game){
        gameChange(game);
        game.characterAdd(this);
    }

    public void smSkillAdd(Skill skill){
        skillAdd(skill);
        skill.characterChange(this);
    }

    public void smSkinAdd(Skin skin){
        skinAdd(skin);
        skin.characterChange(this);
    }

    public void smItemAdd(Item item){
        itemAdd(item);
        item.characterChange(this);
    }

    public void smChangeItems(List<Item> items){
        this.items = items;
        this.items.forEach(a->{a.characterChange(this);});
    }

    public void skinAdd(Skin skin){
        skins.add(skin);
    }

    public void gameChange(Game game){
        this.game = game;
    }

    public void skillAdd(Skill skill){
        skills.add(skill);
    }

    public void itemAdd(Item item) {
        items.add(item);
    }

    public void delCharacter() {this.actYn = false;}

}
