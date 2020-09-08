package com.op.gg.ogj.item.service;

import com.op.gg.ogj.item.model.dto.ItemParam;
import com.op.gg.ogj.item.model.dto.ItemResponse;
import com.op.gg.ogj.item.model.dto.ItemSearch;
import com.op.gg.ogj.item.service.core.ItemCoreService;
import com.op.gg.ogj.item.service.valid.ItemValidService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {

    private final ItemCoreService itemCoreService;
    private final ItemValidService itemValidService;

    @Transactional
    public Long createItem(ItemParam itemParam){
        //123123123123123123123132123123123123
        itemValidService.createItemValid(itemParam);
        return itemCoreService.createItem(itemParam);
    }

    @Transactional
    public Long updateItem(ItemParam itemParam){
        //7777712312312312312312313123132123213123
        //456
        itemValidService.updateItemValid(itemParam);
        return itemCoreService.updateItem(itemParam);
    }

    public Page<ItemResponse> pageItem(ItemSearch itemSearch, Pageable pageable){
        //7777788888123123123123123123123132123
        //789
        itemValidService.pageItemValid(itemSearch);
        return itemCoreService.pageItem(itemSearch, pageable);
    }

    public ItemResponse detailItem(ItemSearch itemSearch){
        //77778989
        itemValidService.detailItemValid(itemSearch);
        return itemCoreService.detailItem(itemSearch);
    }

    @Transactional
    public void delItem(ItemParam itemParam){
        itemValidService.delItemValid(itemParam);
        itemCoreService.delItem(itemParam);
    }

    @Transactional
    public void delItems(ItemParam itemParam){
        itemValidService.delItemsValid(itemParam);
        itemCoreService.delItems(itemParam);
    }

}
