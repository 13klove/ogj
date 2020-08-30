package com.op.gg.ogj.itemSpec.model;

import com.op.gg.ogj.config.baseDate.BaseDate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "item_spec")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemSpec extends BaseDate {

    @Id
    @GeneratedValue
    private Long itemSpecId;

    private String isInfo;

    private Integer damageRate;

    private Integer defenseRate;

    private Boolean actYn;

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

    public void delItemSpec() { this.actYn = false; }

}
