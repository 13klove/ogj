package com.op.gg.ogj.item.model;

import com.op.gg.ogj.config.baseDate.BaseDate;
import com.op.gg.ogj.itemSpec.model.ItemSpec;
import com.op.gg.ogj.character.model.entity.Character;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "item")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item extends BaseDate {

    @Id @GeneratedValue
    private Long itemId;

    private String itemNm;

    private ItemType itemType;

    private Integer price;

    private Boolean actYn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "character_id")
    private Character character;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_spec_id")
    private ItemSpec itemSpec;

    protected Item(String itemNm, ItemType itemType, Integer price){
        this.itemNm = itemNm;
        this.itemType = itemType;
        this.price = price;
    }

    public static Item createItem(String itemNm, ItemType itemType, Integer price){
        return new Item(itemNm, itemType, price).itemCreateDefault();
    }

    public Item itemCreateDefault(){
        this.actYn = actYn;
        return this;
    }

    public void smCharacterChange(Character character){
        characterChange(character);
        character.itemAdd(this);
    }

    public void itemSpecChange(ItemSpec itemSpec){
        this.itemSpec = itemSpec;
    }

    public void characterChange(Character character){
        this.character = character;
    }

    public void delItem() { this.actYn = false; }

}
