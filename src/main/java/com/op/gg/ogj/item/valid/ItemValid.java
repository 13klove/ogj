package com.op.gg.ogj.item.valid;

import com.op.gg.ogj.config.exception.domain.ParamValidException;
import com.op.gg.ogj.config.valid.ObjectValid;
import com.op.gg.ogj.config.valid.ValueValid;
import com.op.gg.ogj.item.model.entity.Item;

public enum ItemValid implements ObjectValid<Item>, ValueValid {

    ITEM_ITEM_ID_LOCK("아이템의ID가 없습니다."){////
        public void validLogic(Long value) {
            if(value==null) throw new ParamValidException("아이템의ID가 없습니다.");
        }
    };

    private String desc;

    ItemValid(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public void validLogic(Item param) {

    }

    @Override
    public void validLogic(Long value) {

    }

    @Override
    public void validLogic(Integer value) {

    }

    @Override
    public void validLogic(String value) {

    }
}
