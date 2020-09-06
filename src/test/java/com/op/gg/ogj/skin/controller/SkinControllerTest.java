package com.op.gg.ogj.skin.controller;

import com.google.common.collect.Lists;
import com.op.gg.ogj.common.CommonController;
import com.op.gg.ogj.skin.model.dto.SkinParam;
import com.op.gg.ogj.skin.repository.SkinJpaRepository;
import com.op.gg.ogj.skin.service.SkinService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.persistence.EntityManager;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SkinControllerTest extends CommonController {

    @Autowired
    SkinService skinService;
    @Autowired
    SkinJpaRepository skinJpaRepository;
    @Autowired
    EntityManager entityManager;
    Long tempId1;
    Long tempId2;
    Long tempId3;
    List<Long> tempsId = Lists.newArrayList();

    @BeforeEach
    @Transactional
    public void dumpData(){
        SkinParam skinParam1 = SkinParam.builder().characterId(362l).skinNm("마린 부스터").price(5000l).build();
        SkinParam skinParam2 = SkinParam.builder().characterId(362l).skinNm("매딕 오피스").price(2500l).build();
        SkinParam skinParam3 = SkinParam.builder().characterId(362l).skinNm("럴커 덫").price(3000l).build();


        tempId1 = skinService.createSkin(skinParam1);
        tempId2 = skinService.createSkin(skinParam2);
        tempId3 = skinService.createSkin(skinParam3);


        tempsId.add(tempId1);
        tempsId.add(tempId2);
        tempsId.add(tempId3);
    }

    @Test
    @Transactional
    public void createSkinTest() throws Exception{
        SkinParam skinParam = SkinParam.builder().characterId(359l).skinNm("고스트 유령갑옷").price(3000l).build();
        String str = om.writeValueAsString(skinParam);

        mm.perform(post("/api/skin").content(str).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    public void updateSkinTest() throws Exception{
        SkinParam skinParam = SkinParam.builder().characterId(362l).skinNm("벌처의 날렵").price(1500l).build();
        String str = om.writeValueAsString(skinParam);

        mm.perform(put("/api/skin/{id}", tempId1).content(str).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    public void pageSkinTest() throws Exception{
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("characterId", "362");
        params.add("page", "0");
        params.add("size", "10");

        mm.perform(get("/api/skin").params(params))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    public void detailSkinTest() throws Exception{
        mm.perform(get("/api/skin/{id}", tempId1))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    public void delSkinTest() throws Exception{
        mm.perform(put("/api/skin/{id}/del", tempId1).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    public void delsSkinTest() throws Exception{
        SkinParam skinParam = SkinParam.builder().skinsId(tempsId).build();
        String str = om.writeValueAsString(skinParam);

        mm.perform(post("/api/skin/dels").content(str).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
