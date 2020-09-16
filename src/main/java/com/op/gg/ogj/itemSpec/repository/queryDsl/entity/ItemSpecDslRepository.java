package com.op.gg.ogj.itemSpec.repository.queryDsl.entity;

import java.util.List;

public interface ItemSpecDslRepository {

    void updateItemSpecByItemSpecIds(List<Long> itemSpecIds);

}
