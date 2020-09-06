package com.op.gg.ogj.skin.service;

import com.google.common.collect.Lists;
import com.op.gg.ogj.skin.model.dto.SkinParam;
import com.op.gg.ogj.skin.model.dto.SkinResponse;
import com.op.gg.ogj.skin.model.dto.SkinSearch;
import com.op.gg.ogj.skin.repository.SkinJpaRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@SpringBootTest
public class SkinServiceTest {

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
    public void createSkinTest(){
        SkinParam skinParam = SkinParam.builder().characterId(359l).skinNm("고스트 유령갑옷").price(3000l).build();

        Long id = skinService.createSkin(skinParam);

        assertNotNull(id);

        log.info("skin: {}", id);
    }

    @Test
    @Transactional
    public void updateSkinTest(){
        SkinParam skinParam = SkinParam.builder().characterId(362l).skinId(tempId1).skinNm("벌처의 날렵").price(1500l).build();

        Long id = skinService.updateSkin(skinParam);
        entityManager.flush();
        assertNotNull(id);

        log.info("skin: {}", id);
    }

    @Test
    @Transactional
    public void pageSkinTest(){
        SkinSearch skinSearch = SkinSearch.builder().characterId(362l).build();
        PageRequest page = PageRequest.of(0, 10);

        Page<SkinResponse> pages = skinService.pageSkin(skinSearch, page);

        pages.forEach(a->log.info("page: {}", a));

        assertNotNull(pages);
    }

    @Test
    @Transactional
    public void detailSkinTest(){
        SkinSearch skinSearch = SkinSearch.builder().skinId(tempId1).build();

        SkinResponse skinResponse = skinService.detailSkin(skinSearch);

        assertNotNull(skinResponse);

        log.info("skin: {}", skinResponse);
    }

    @Test
    @Transactional
    public void delSkinTest(){
        SkinParam skinParam = SkinParam.builder().skinId(tempId1).build();

        skinService.delSkin(skinParam);
        entityManager.flush();
    }

    @Test
    @Transactional
    public void delSkinsTest(){
        SkinParam skinParam = SkinParam.builder().skinsId(tempsId).build();

        skinService.delSkins(skinParam);
        entityManager.flush();
    }

}
