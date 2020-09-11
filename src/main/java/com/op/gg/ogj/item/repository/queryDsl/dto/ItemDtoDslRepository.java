package com.op.gg.ogj.item.repository.queryDsl.dto;

import com.op.gg.ogj.item.model.ItemType;
import com.op.gg.ogj.item.model.dto.ItemResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemDtoDslRepository {

    Page<ItemResponse> pageItem(Long characterId, String itemNm, ItemType itemType, Pageable pageable);

    ItemResponse detailItem(Long characterId, Long itemId);

}
