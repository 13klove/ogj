package com.op.gg.ogj.ost.valid;

import com.op.gg.ogj.config.exception.domain.ParamValidException;
import com.op.gg.ogj.ost.model.entity.Ost;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

public enum OstValid {

    OST_OST_ID_LOCK("OST Id가 없습니다."){
        public void validLogic(Long value){ if(value==null) throw new ParamValidException("OST Id가 없습니다."); }
    },
    OST_OST_IDS_LOCK("OST Id가 없습니다."){
        public void validLogic(List<Long> value){ if(value==null||value.isEmpty()) throw new ParamValidException("OST Id가 없습니다."); }
    },
    OST_OST_NM_LOCK("OST 이름이 없습니다."){
        public void validLogic(String value){ if(!StringUtils.hasText(value)) throw new ParamValidException("OST 이름이 없습니다."); }
    },
    OST_OST_NM_EXIT("이미 등록된 OST 입니다."){
        public void validLogic(Ost value){ if(value!=null) throw new ParamValidException("이미 등록된 ost 입니다."); }
    },
    OST_OST_NO_HAVE("해당 OST Id는 존재하지 않습니다."){
        public void validLogic(Optional<Ost> value){ value.orElseThrow(() -> new ParamValidException("해당 OST Id는 존재하지 않습니다.")); }
    }
    ;

    private String desc;

    OstValid(String desc){
        this.desc = desc;
    }

    public void validLogic(Long value) { }

    public void validLogic(List<Long> value) { }

    public void validLogic(String value) { }

    public void validLogic(Ost value) {}

    public void validLogic(Optional<Ost> value) {}

}
