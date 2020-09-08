package com.op.gg.ogj.item.service.valid;

import com.op.gg.ogj.character.valid.CharacterValid;
import com.op.gg.ogj.item.model.dto.ItemParam;
import com.op.gg.ogj.item.model.dto.ItemSearch;
import com.op.gg.ogj.item.valid.ItemValid;
import com.op.gg.ogj.itemSpec.valid.ItemSpec;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class ItemValidService {



    public void createItemValid(ItemParam itemParam) {
        CharacterValid.CHARACTER_ID_LOCK.validLogic(itemParam.getCharacterId());
        ItemValid.ITEM_ITEM_NM_LOCK.validLogic(itemParam.getItemNm());
        ItemValid.ITEM_ITEM_TYPE_LOCK.validLogic(itemParam.getItemType());
        ItemValid.ITEM_PRICE_LOCK.validLogic(itemParam.getPrice());
        ItemSpec.ITEM_SPEC_IS_INFO_LOCK.validLogic(itemParam.getIsInfo());
        ItemSpec.ITEM_SPEC_DAMAGE_RATE_LOCK.validLogic(itemParam.getDamageRate());
        ItemSpec.ITEM_SPEC_DEFENSE_RATE_LOCK.validLogic(itemParam.getDefenseRate());
    }

    public void updateItemValid(ItemParam itemParam) {
    }

    public void pageItemValid(ItemSearch itemSearch) {
    }

    public void detailItemValid(ItemSearch itemSearch) {
    }

    public void delItemValid(ItemParam itemParam) {
    }

    public void delItemsValid(ItemParam itemParam) {
    }
}
