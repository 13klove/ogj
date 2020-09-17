package com.op.gg.ogj.ost.controller;

import com.op.gg.ogj.ost.model.dto.OstParam;
import com.op.gg.ogj.ost.model.dto.OstResponse;
import com.op.gg.ogj.ost.model.dto.OstSearch;
import com.op.gg.ogj.ost.service.OstService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@Api(tags = "ost")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ost")
public class OstController {

    private final OstService ostService;

    @PostMapping
    @ApiOperation(value = "ost 생성", notes = "ost 생성")
    public Long createOst(@RequestBody OstParam ostParam){
        return ostService.createOst(ostParam);
    }

    @PutMapping("{id}")
    @ApiOperation(value = "ost 수정", notes = "ost 수정")
    public Long updateOst(@PathVariable("id") Long ostId, @RequestBody OstParam ostParam){
        ostParam.setOstId(ostId);
        return ostService.updateOst(ostParam);
    }

    @GetMapping
    @ApiOperation(value = "ost 페이지 조회", notes = "ost 페이지 조회")
    public Page<OstResponse> pageOst(OstSearch ostSearch, Pageable pageable){
        return ostService.pageOst(ostSearch, pageable);
    }

    @GetMapping("{id}")
    @ApiOperation(value = "ost 상세 조회", notes = "ost 상세 조회")
    public OstResponse detailOst(@PathVariable("id") Long ostId){
        return ostService.detailOst(OstSearch.builder().ostId(ostId).build());
    }

    @PutMapping("{id}/del")
    @ApiOperation(value = "ost 삭제", notes = "ost 삭제")
    public void delOst(@PathVariable("id") Long ostId){
        ostService.delOst(OstParam.builder().ostId(ostId).build());
    }

    @PostMapping("del")
    @ApiOperation(value = "ost 다중 삭제", notes = "ost 다중 삭제")
    public void delOsts(@RequestBody OstParam ostParam){
        ostService.delOsts(ostParam);
    }

}
