package com.op.gg.ogj.itemSpec.model;

import com.op.gg.ogj.config.baseDate.BaseDate;
import com.op.gg.ogj.item.model.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "item_spec")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemSpec extends BaseDate implements Persistable<Long> {

    @Id
    private Long itemId;

    private String isInfo;

    private Integer damageRate;

    private Integer defenseRate;

    private Boolean actYn;

    @MapsId("itemId")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    protected ItemSpec(String isInfo, Integer damageRate, Integer defenseRate){
        this.isInfo = isInfo;
        this.damageRate = damageRate;
        this.defenseRate = defenseRate;
    }

    public static ItemSpec createItemSpec(String isInfo, Integer damageRate, Integer defenseRate){
        return new ItemSpec(isInfo, damageRate, defenseRate).itemSpecCreateDefault();
    }

    public ItemSpec itemSpecCreateDefault(){
        this.actYn = true;
        return this;
    }

    public void smItemChange(Item item){
        itemChange(item);
        item.itemSpecChange(this);
    }

    public void itemChange(Item item) {
        this.item = item;
        if(item.getItemId() != null){
            this.itemId = item.getItemId();
        }
    }

    @Override
    public Long getId() {
        return itemId;
    }

    @Override
    public boolean isNew() {
        return getCreateDt() != null;
    }
}
