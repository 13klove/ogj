package com.op.gg.ogj.ost.service;

import com.op.gg.ogj.ost.model.dto.OstParam;
import com.op.gg.ogj.ost.model.dto.OstResponse;
import com.op.gg.ogj.ost.model.dto.OstSearch;
import com.op.gg.ogj.ost.service.core.OstCoreService;
import com.op.gg.ogj.ost.service.valid.OstValidService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OstService {

    private final OstCoreService ostCoreService;
    private final OstValidService ostValidService;

    @Transactional
    public Long createOst(OstParam ostParam){
        ostValidService.createOstValid(ostParam);
        return ostCoreService.createOst(ostParam);
    }

    @Transactional
    public Long updateOst(OstParam ostParam){
        ostValidService.updateOstValid(ostParam);
        return ostCoreService.updateOst(ostParam);
    }

    @Transactional(readOnly = true)
    public Page<OstResponse> pageOst(OstSearch ostSearch, Pageable pageable){
        ostValidService.pageOstValid(ostSearch);
        return ostCoreService.pageOst(ostSearch, pageable);
    }

    @Transactional(readOnly = true)
    public OstResponse detailOst(OstSearch ostSearch){
        ostValidService.detailOstValid(ostSearch);
        return ostCoreService.detailOst(ostSearch);
    }

    @Transactional
    public void delOst(OstParam ostParam){
        ostValidService.delOst(ostParam);
        ostCoreService.delOst(ostParam);
    }

    @Transactional
    public void delOsts(OstParam ostParam){
        ostValidService.delOsts(ostParam);
        ostCoreService.delOst(ostParam);
    }

}
