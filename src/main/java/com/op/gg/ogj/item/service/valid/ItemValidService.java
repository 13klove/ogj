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
    }

    public void updateItemValid(ItemParam itemParam) {
    }

    public void pageItemValid(ItemSearch itemSearch) {
        //1111111111
    }

    public void detailItemValid(ItemSearch itemSearch) {
        //1111111111
    }

    public void delItemValid(ItemParam itemParam) {
        //1111111111
    }

    public void delItemsValid(ItemParam itemParam) {
    }
}
