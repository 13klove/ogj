package com.op.gg.ogj.item.service.core;

import com.op.gg.ogj.item.model.dto.ItemParam;
import com.op.gg.ogj.item.model.dto.ItemResponse;
import com.op.gg.ogj.item.model.dto.ItemSearch;
import com.op.gg.ogj.item.repository.ItemJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class ItemCoreService {

    private final ItemJpaRepository itemJpaRepository;

    public Long createItem(ItemParam itemParam) {
        return null;
    }

    public Long updateItem(ItemParam itemParam) {
        return null;
    }

    public Page<ItemResponse> pageItem(ItemSearch itemSearch, Pageable pageable) {
        return null;
    }

    public ItemResponse detailItem(ItemSearch itemSearch) {
        return null;////abc
    }

    public void delItem(ItemParam itemParam) {
    }

    public void delItems(ItemParam itemParam) {
    }
}
