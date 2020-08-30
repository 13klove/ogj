package com.op.gg.ogj.ost.repository.queryDsl.dto;

import com.op.gg.ogj.ost.model.dto.OstResponse;
import com.op.gg.ogj.ost.model.dto.OstSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OstDtoDslRepository {

    Page<OstResponse> findPageOst(OstSearch ostSearch, Pageable pageable);

    OstResponse findDetailOst(OstSearch ostSearch);

}
