package com.op.gg.ogj.config.valid;

import com.op.gg.ogj.item.model.ItemType;

import java.util.List;

public interface ValueValid {

    void validLogic(Long value);
    void validLogic(Integer value);
    void validLogic(String value);
    void validLogic(ItemType value);
    void validLogic(List<Long> value);

}
