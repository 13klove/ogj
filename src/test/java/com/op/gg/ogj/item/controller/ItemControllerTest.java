package com.op.gg.ogj.item.controller;

import com.google.common.collect.Lists;
import com.op.gg.ogj.common.CommonController;
import com.op.gg.ogj.item.model.ItemType;
import com.op.gg.ogj.item.model.dto.ItemParam;
import com.op.gg.ogj.item.repository.ItemJpaRepository;
import com.op.gg.ogj.item.service.ItemService;
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

public class ItemControllerTest extends CommonController {

    @Autowired
    ItemService itemService ;
    @Autowired
    ItemJpaRepository itemJpaRepository;
    @Autowired
    EntityManager entityManager;
    Long tempId1;
    Long tempId2;
    Long tempId3;
    List<Long> tempsId = Lists.newArrayList();

    @BeforeEach
    @Transactional
    public void dumpData(){
        ItemParam itemParam1 = ItemParam.builder().characterId(362l).itemNm("k2").itemType(ItemType.ATTACK).price(5000)
                .isInfo("마린 소총").damageRate(50).defenseRate(0).build();
        ItemParam itemParam2 = ItemParam.builder().characterId(362l).itemNm("아머 헬").itemType(ItemType.ATTACK).price(2500)
                .isInfo("마린 창").damageRate(10).defenseRate(0).build();
        ItemParam itemParam3 = ItemParam.builder().characterId(362l).itemNm("아머 부츠").itemType(ItemType.ARMOR).price(3000)
                .isInfo("마린 신발").damageRate(0).defenseRate(10).build();


        tempId1 = itemService.createItem(itemParam1);
        tempId2 = itemService.createItem(itemParam2);
        tempId3 = itemService.createItem(itemParam3);


        tempsId.add(tempId1);
        tempsId.add(tempId2);
        tempsId.add(tempId3);
    }

    @Test
    @Transactional
    public void createItemTest() throws Exception{
        ItemParam itemParam = ItemParam.builder().characterId(362l).itemNm("M-4").itemType(ItemType.ATTACK).price(5000)
                .isInfo("마린 소총").damageRate(50).defenseRate(0).build();
        String str = om.writeValueAsString(itemParam);

        mm.perform(post("/api/item").content(str).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    public void updateItemTest() throws Exception{
        ItemParam itemParam = ItemParam.builder().characterId(362l).itemId(tempId1).itemNm("M-4").itemType(ItemType.ATTACK).price(5000)
                .isInfo("마린 소총").damageRate(50).defenseRate(0).build();
        String str = om.writeValueAsString(itemParam);

        mm.perform(put("/api/item/{id}", tempId1).content(str).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    public void pageItemTest() throws Exception{
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("characterId", "362");
        params.add("page", "0");
        params.add("size", "10");

        mm.perform(get("/api/item").params(params))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    public void detailItemTest() throws Exception{
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("characterId", "362");
        mm.perform(get("/api/item/{id}", tempId1).params(params))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    public void delItemTest() throws Exception{
        mm.perform(put("/api/item/{id}/del", tempId1).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    public void delsItemTest() throws Exception{
        ItemParam itemParam = ItemParam.builder().itemIds(tempsId).build();
        String str = om.writeValueAsString(itemParam);

        mm.perform(post("/api/item/dels").content(str).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
