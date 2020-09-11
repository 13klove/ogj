package com.op.gg.ogj.item.repository;

import com.op.gg.ogj.item.model.entity.Item;
import com.op.gg.ogj.item.repository.queryDsl.entity.ItemDslRepository;
import com.op.gg.ogj.item.repository.queryDsl.dto.ItemDtoDslRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemJpaRepository extends JpaRepository<Item, Long>, ItemDtoDslRepository, ItemDslRepository {

    Item findItemByCharacter_CharacterIdAndItemNmAndActYnTrue(Long characterId, String itemNm);

    Item findItemByCharacter_CharacterIdAndItemIdAndItemNmAndActYnTrue(Long characterId, Long itemId, String itemNm);

}
