package com.op.gg.ogj.item.service.core;

import com.op.gg.ogj.character.model.entity.Character;
import com.op.gg.ogj.character.repository.CharacterJpaRepository;
import com.op.gg.ogj.item.model.dto.ItemParam;
import com.op.gg.ogj.item.model.dto.ItemResponse;
import com.op.gg.ogj.item.model.dto.ItemSearch;
import com.op.gg.ogj.item.model.entity.Item;
import com.op.gg.ogj.item.repository.ItemJpaRepository;
import com.op.gg.ogj.itemSpec.model.ItemSpec;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemCoreService {

    private final ItemJpaRepository itemJpaRepository;
    private final CharacterJpaRepository characterJpaRepository;

    @Transactional
    public Long createItem(ItemParam itemParam) {
        Character character = characterJpaRepository.findById(itemParam.getCharacterId()).get();
        ItemSpec itemSpec = ItemSpec.createItemSpec(itemParam.getIsInfo(), itemParam.getDamageRate(), itemParam.getDefenseRate());


        Item item = Item.createItem(itemParam.getItemNm(), itemParam.getItemType(), itemParam.getPrice());
        item.smCharacterChange(character);
        item.changeIemSpec(itemSpec);

        itemJpaRepository.save(item);

        return item.getItemId();
    }

    @Transactional
    public Long updateItem(ItemParam itemParam) {
        Item item = itemJpaRepository.findItemByItemIdAcUpdate(itemParam.getItemId());
        item.updateItem(itemParam.getItemNm(), itemParam.getItemType(), itemParam.getPrice());
        item.getItemSpec().updateItem(itemParam.getIsInfo(), itemParam.getDamageRate(), itemParam.getDefenseRate());
        return item.getItemId();
    }

    public Page<ItemResponse> pageItem(ItemSearch itemSearch, Pageable pageable) {
        return itemJpaRepository.pageItem(itemSearch.getCharacterId(), itemSearch.getItemNm(), itemSearch.getItemType(), pageable);
    }

    public ItemResponse detailItem(ItemSearch itemSearch) {
        return itemJpaRepository.detailItem(itemSearch.getCharacterId(), itemSearch.getItemId());
    }

    @Transactional
    public void delItem(ItemParam itemParam) {
        Item item = itemJpaRepository.findDelItemByItemId(itemParam.getItemId());
        item.delItem();
        item.getItemSpec().delItemSpec();
    }

    @Transactional
    public void delItems(ItemParam itemParam) {
        List<Item> items = itemJpaRepository.findDelItemsByItemIds(itemParam.getItemIds());
        items.forEach(a->{a.delItem(); a.getItemSpec().delItemSpec();});
    }
}
