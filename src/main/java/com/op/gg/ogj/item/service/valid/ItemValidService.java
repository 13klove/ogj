package com.op.gg.ogj.item.service.valid;

import com.op.gg.ogj.item.model.dto.ItemParam;
import com.op.gg.ogj.item.model.dto.ItemSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class ItemValidService {

    public void createItemValid(ItemParam itemParam) {
        //123123
    }

    public void updateItemValid(ItemParam itemParam) {
        //123123
    }

    public void pageItemValid(ItemSearch itemSearch) {
        //123
    }

    public void detailItemValid(ItemSearch itemSearch) {
    }

    public void delItemValid(ItemParam itemParam) {
    }

    public void delItemsValid(ItemParam itemParam) {
    }
}
