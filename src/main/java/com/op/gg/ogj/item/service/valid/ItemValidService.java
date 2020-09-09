package com.op.gg.ogj.item.service.valid;

import com.op.gg.ogj.character.model.entity.Character;
import com.op.gg.ogj.character.repository.CharacterJpaRepository;
import com.op.gg.ogj.character.valid.CharacterValid;
import com.op.gg.ogj.item.model.dto.ItemParam;
import com.op.gg.ogj.item.model.dto.ItemSearch;
import com.op.gg.ogj.item.model.entity.Item;
import com.op.gg.ogj.item.repository.ItemJpaRepository;
import com.op.gg.ogj.item.valid.ItemValid;
import com.op.gg.ogj.itemSpec.valid.ItemSpec;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemValidService {

    private final ItemJpaRepository itemJpaRepository;
    private final CharacterJpaRepository characterJpaRepository;

    public void createItemValid(ItemParam itemParam) {
        createUpdateValid(itemParam);
        Item item = itemJpaRepository.findItemByCharacter_CharacterIdAndItemNmAndActYnTrue(itemParam.getCharacterId(), itemParam.getItemNm());
        ItemValid.ITEM_ITEM_NM_EXIT.validLogic(item);
    }

    public void updateItemValid(ItemParam itemParam) {
        createUpdateValid(itemParam);
        Item item = itemJpaRepository.findItemByCharacter_CharacterIdAndItemIdAndItemNmAndActYnTrue(itemParam.getCharacterId(), itemParam.getItemId(), itemParam.getItemNm());
        ItemValid.ITEM_ITEM_NM_EXIT.validLogic(item);
    }

    public void pageItemValid(ItemSearch itemSearch) {
        CharacterValid.CHARACTER_ID_LOCK.validLogic(itemSearch.getCharacterId());
    }

    public void detailItemValid(ItemSearch itemSearch) {
        CharacterValid.CHARACTER_ID_LOCK.validLogic(itemSearch.getCharacterId());
        ItemValid.ITEM_ITEM_ID_LOCK.validLogic(itemSearch.getItemId());
    }

    public void delItemValid(ItemParam itemParam) {
        ItemValid.ITEM_ITEM_ID_LOCK.validLogic(itemParam.getItemId());
    }

    public void delItemsValid(ItemParam itemParam) {
        ItemValid.ITEM_ITEM_IDS_LOCK.validLogic(itemParam.getItemIds());
    }

    private void createUpdateValid(ItemParam itemParam) {
        CharacterValid.CHARACTER_ID_LOCK.validLogic(itemParam.getCharacterId());

        Optional<Character> character = characterJpaRepository.findById(itemParam.getCharacterId());
        CharacterValid.CHARACTER_NO_HAVE.validLogic(character);

        ItemValid.ITEM_ITEM_NM_LOCK.validLogic(itemParam.getItemNm());
        ItemValid.ITEM_ITEM_TYPE_LOCK.validLogic(itemParam.getItemType());
        ItemValid.ITEM_PRICE_LOCK.validLogic(itemParam.getPrice());

        ItemSpec.ITEM_SPEC_IS_INFO_LOCK.validLogic(itemParam.getIsInfo());
        ItemSpec.ITEM_SPEC_DAMAGE_RATE_LOCK.validLogic(itemParam.getDamageRate());
        ItemSpec.ITEM_SPEC_DEFENSE_RATE_LOCK.validLogic(itemParam.getDefenseRate());
    }

}
