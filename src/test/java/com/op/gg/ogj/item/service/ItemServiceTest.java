package com.op.gg.ogj.item.service;

import com.google.common.collect.Lists;
import com.op.gg.ogj.item.model.ItemType;
import com.op.gg.ogj.item.model.dto.ItemParam;
import com.op.gg.ogj.item.model.dto.ItemResponse;
import com.op.gg.ogj.item.model.dto.ItemSearch;
import com.op.gg.ogj.item.repository.ItemJpaRepository;
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
public class ItemServiceTest {

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
    public void createItemTest(){
        ItemParam itemParam = ItemParam.builder().characterId(362l).itemNm("M-4").itemType(ItemType.ATTACK).price(5000)
                .isInfo("마린 소총").damageRate(50).defenseRate(0).build();

        Long id = itemService.createItem(itemParam);

        assertNotNull(id);

        log.info("item: {}", id);
    }

    @Test
    @Transactional
    public void updateItemTest(){
        ItemParam itemParam = ItemParam.builder().characterId(362l).itemId(tempId1).itemNm("M-4").itemType(ItemType.ATTACK).price(5000)
                .isInfo("마린 소총").damageRate(50).defenseRate(0).build();

        Long id = itemService.updateItem(itemParam);
        entityManager.flush();
        assertNotNull(id);

        log.info("item: {}", id);
    }

    @Test
    @Transactional
    public void pageItemTest(){
        ItemSearch itemSearch = ItemSearch.builder().characterId(362l).build();
        PageRequest page = PageRequest.of(0, 10);

        Page<ItemResponse> pages = itemService.pageItem(itemSearch, page);

        pages.forEach(a->log.info("page: {}", a));

        assertNotNull(pages);
        System.out.println(pages);
    }

    @Test
    @Transactional
    public void detailItemTest(){
        ItemSearch itemSearch = ItemSearch.builder().characterId(362l).itemId(tempId1).build();

        ItemResponse itemResponse = itemService.detailItem(itemSearch);

        assertNotNull(itemResponse);

        log.info("item: {}", itemResponse);
    }

    @Test
    @Transactional
    public void delSkinTest(){
        ItemParam itemParam = ItemParam.builder().itemId(tempId1).build();

        itemService.delItem(itemParam);
        entityManager.flush();
    }

    @Test
    @Transactional
    public void delItemsTest(){
        ItemParam itemParam = ItemParam.builder().itemIds(tempsId).build();

        itemService.delItems(itemParam);
        entityManager.flush();
    }

}
