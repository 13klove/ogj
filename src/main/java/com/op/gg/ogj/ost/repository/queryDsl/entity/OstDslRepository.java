package com.op.gg.ogj.ost.repository.queryDsl.entity;

import com.op.gg.ogj.ost.model.dto.OstParam;
import com.op.gg.ogj.ost.model.entity.Ost;

import java.util.List;

public interface OstDslRepository {

    Ost findOstGameMapByOstId(Long ostId);

    Ost findOstInMapByOstId(OstParam ostParam);

    void updateOstActYn(List<Long> gameIds);
}
