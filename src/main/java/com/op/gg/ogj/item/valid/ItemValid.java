package com.op.gg.ogj.item.valid;

import com.op.gg.ogj.config.exception.domain.ParamValidException;
import com.op.gg.ogj.item.model.ItemType;
import com.op.gg.ogj.item.model.entity.Item;
import org.springframework.util.StringUtils;

import java.util.List;

public enum ItemValid {

    ITEM_ITEM_ID_LOCK("아이템의ID가 없습니다."){
        public void validLogic(Long value) {
            if(value==null) throw new ParamValidException("아이템의ID가 없습니다.");
        }
    },
    ITEM_ITEM_IDS_LOCK("아이템의IDs가 없습니다."){
        public void validLogic(List<Long> value) { if(value==null||value.isEmpty()) throw new ParamValidException("아이템의ID가 없습니다."); }
    },
    ITEM_ITEM_NM_LOCK("아이템의 이름이 없습니다."){
        public void validLogic(String value){ if(!StringUtils.hasText(value)) throw new ParamValidException("아이템의 이름이 없습니다."); }
    },
    ITEM_ITEM_TYPE_LOCK("아이템 타입이 없습니다."){
        public void validLogic(ItemType value){
            if(value==null) throw new ParamValidException("아이템 타입이 없습니다.");
        }
    },
    ITEM_PRICE_LOCK("아이템 가격이 없습니다."){
        public void validLogic(Integer value){
            if(value==null) throw new ParamValidException("아이템 가격이 없습니다.");
        }
    },
    ITEM_ITEM_NM_EXIT("이미 존재하는 아이템이름 입니다."){
        public void validLogic(Item value){ if(value!=null) throw new ParamValidException("이미 존재하는 아이템이름 입니다."); }
    }
    ;

    private String desc;

    ItemValid(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void validLogic(Long value) { }

    public void validLogic(List<Long> value) { }

    public void validLogic(Integer value) { }

    public void validLogic(String value) { }

    public void validLogic(ItemType value) {}

    public void validLogic(Item value) {}

}
