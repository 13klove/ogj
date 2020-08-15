package com.op.gg.ogj.map.model.entity;

import com.google.common.collect.Lists;
import com.op.gg.ogj.config.baseDate.BaseDate;
import com.op.gg.ogj.ost.model.entity.Ost;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@Table(name = "map")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Map extends BaseDate {

    @Id @GeneratedValue
    private Long mapId;

    private String mapNm;

    private Boolean actYn;

    @OneToMany(mappedBy = "map")
    private List<Ost> osts = Lists.newArrayList();

    protected Map(String mapNm){
        this.mapNm = mapNm;
    }

    public static Map createMap(String mapNm){
        return new Map(mapNm).mapCreateDefault();
    }

    public Map mapCreateDefault(){
        this.actYn = true;
        return this;
    }

    public void smOstAdd(Ost ost){
        ostAdd(ost);
        ost.mapChange(this);
    }

    public void ostAdd(Ost ost){
        this.osts.add(ost);
    }

}
