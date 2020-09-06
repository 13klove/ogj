package com.op.gg.ogj.skill.controller;

import com.op.gg.ogj.common.CommonController;
import com.op.gg.ogj.skill.model.SkillType;
import com.op.gg.ogj.skill.model.dto.SkillParam;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SkillControllerTest extends CommonController {

    @Test
    @Transactional
    public void createSkillTest() throws Exception{
        SkillParam skillParam = SkillParam.builder().characterId(359l).skillNm("힐링팩").useEnergy(15).useLife(80).skillType(SkillType.HEAL)
                .damage(0).ulimateYn(false).build();
        String str = om.writeValueAsString(skillParam);

        mm.perform(post("/api/skill").content(str).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    public void updateSkillTest() throws Exception{
        SkillParam skillParam = SkillParam.builder().characterId(359l).skillNm("뉴클리어 어택").useEnergy(15).useLife(80).skillType(SkillType.HEAL)
                .damage(0).ulimateYn(false).build();
        String str = om.writeValueAsString(skillParam);

        mm.perform(put("/api/skill/{id}", 393).content(str).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    public void listSkillTest() throws Exception{
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("characterId", "359");
        params.add("page", "0");
        params.add("size", "10");

        mm.perform(get("/api/skill").params(params))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    public void detailSkillTest() throws Exception{
        mm.perform(get("/api/skill/{id}", 393))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    public void delSkillTest() throws Exception{
        mm.perform(put("/api/skill/{id}/del", 393).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    public void delsSkillTest() throws Exception{
        SkillParam skillParam = SkillParam.builder().skillsId(Arrays.asList(393l, 392l)).build();
        String str = om.writeValueAsString(skillParam);

        mm.perform(post("/api/skill/dels").content(str).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
