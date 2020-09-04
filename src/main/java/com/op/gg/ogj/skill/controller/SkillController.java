package com.op.gg.ogj.skill.controller;

import com.op.gg.ogj.config.domain.response.ResponseDto;
import com.op.gg.ogj.skill.model.dto.SkillParam;
import com.op.gg.ogj.skill.model.dto.SkillSearch;
import com.op.gg.ogj.skill.service.SkillService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = {"skill"})
@RequiredArgsConstructor
@RequestMapping("/api/skill")
public class SkillController {

    private final SkillService skillService;

    @PostMapping
    @ApiOperation(notes = "스킬등록", value = "스킬등록")
    public ResponseDto createSkill(@RequestBody SkillParam skillParam){
        return ResponseDto.of(skillService.createSkill(skillParam));
    }

    @PutMapping("{id}")
    @ApiOperation(notes = "스킬수정", value = "스킬수정")
    public ResponseDto updateSkill(@PathVariable("id") Long skillId, @RequestBody SkillParam skillParam){
        skillParam.setSkillId(skillId);
        return ResponseDto.of(skillService.updateSkill(skillParam));
    }

    @GetMapping
    @ApiOperation(notes = "스킬리스트", value = "스킬리스트")
    public ResponseDto listSkill(SkillSearch skillSearch){
        return ResponseDto.of(skillService.listSkill(skillSearch));
    }

    @GetMapping("{id}")
    @ApiOperation(notes = "스킬상세", value = "스킬상세")
    public ResponseDto detailSkill(@PathVariable("id") Long skillId, SkillSearch skillSearch){
        skillSearch.setSkillId(skillId);
        return ResponseDto.of(skillService.detailSkill(skillSearch));
    }

    @PutMapping("{id}/del")
    @ApiOperation(notes = "스킬삭제", value = "스킬삭제")
    public void delSkill(@PathVariable("id") Long skillId){
        SkillParam skillParam = SkillParam.builder().skillId(skillId).build();
        skillService.delSkill(skillParam);
    }

    @PostMapping("dels")
    @ApiOperation(notes = "스킬 복수 삭제", value = "스킬 복수 삭제")
    public void delSkills(@RequestBody SkillParam skillParam){
        skillService.delSkills(skillParam);
    }
}
