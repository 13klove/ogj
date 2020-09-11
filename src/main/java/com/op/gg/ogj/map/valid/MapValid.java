package com.op.gg.ogj.map.valid;

import com.op.gg.ogj.config.exception.domain.ParamValidException;
import com.op.gg.ogj.map.model.entity.Map;

import java.util.List;

public enum MapValid {

    MAP_MAP_ID_LOCK("맵 ID가 없습니다."){
        public void validLogic(Long value){ if(value==null) throw new ParamValidException("맵 ID가 없습니다."); }
    },
    MAP_MAP_NM_LOCK("맵 이름이 없습니다."){
        public void validLogic(String value){ if(value==null) throw new ParamValidException("맵 이름이 없습니다."); }
    },
    MAP_MAP_EXIT("이미 등록된 맵입니다."){
        public void validLogic(Map value){ if(value!=null) throw new ParamValidException("이미 등록된 맵입니다."); }
    },
    MAP_MAP_NOT_EXIT("등록하려는 맵은 없습니다."){
        public void validLogic(Map value){ if(value==null) throw new ParamValidException("등록하려는 맵은 없습니다."); }
    };

    private String desc;

    MapValid(String desc){
        this.desc = desc;
    }

    public void validLogic(Long value) { }

    public void validLogic(List<Long> value) { }

    public void validLogic(Integer value) { }

    public void validLogic(String value) { }

    public void validLogic(Map value) {}

}
