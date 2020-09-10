package com.op.gg.ogj.item.repository.queryDsl.entity;

import com.op.gg.ogj.item.model.entity.Item;

import java.util.List;

public interface ItemDslRepository {

    Item findItemByItemIdAcUpdate(Long itemId);

    Item findDelItemByItemId(Long itemId);

    List<Item> findDelItemsByItemIds(List<Long> itemIds);

}
