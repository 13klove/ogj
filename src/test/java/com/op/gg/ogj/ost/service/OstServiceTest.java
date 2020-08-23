package com.op.gg.ogj.ost.service;

import com.op.gg.ogj.config.exception.domain.ParamValidException;
import com.op.gg.ogj.ost.model.dto.OstParam;
import com.op.gg.ogj.ost.model.dto.OstResponse;
import com.op.gg.ogj.ost.model.dto.OstSearch;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
@SpringBootTest
public class OstServiceTest {

    @Autowired
    OstService ostService;

    @Test
    @Transactional
    public void createOstTest_valid_gameId(){
        //given
        OstParam op = OstParam.builder().ostNm("스타 테란 주제곡").build();

        //when//then
        Throwable exception =  assertThrows(ParamValidException.class, () -> {
            ostService.createOst(op);
        });

        log.info("err msg: {}", exception.getMessage());
    }

    @Test
    @Transactional
    public void createOstTest_valid_ostNm(){
        //given
        OstParam op = OstParam.builder().gameId(3l).build();

        //when//then
        Throwable exception =  assertThrows(ParamValidException.class, () -> {
            ostService.createOst(op);
        });

        log.info("err msg: {}", exception.getMessage());
    }

    @Test
    @Transactional
    public void createOstTest_valid_ostNm_overlap(){
        //given
        OstParam op = OstParam.builder().gameId(3l).ostNm("terran ost").build();

        //when//then
        Throwable exception =  assertThrows(ParamValidException.class, () -> {
            ostService.createOst(op);
        });

        log.info("err msg: {}", exception.getMessage());
    }

    @Test
    @Transactional
    public void createOstTest_valid(){
        //given
        OstParam op = OstParam.builder().gameId(3l).ostNm("commend center ost").build();

        //when
        Long ostId = ostService.createOst(op);

        //then
        assertNotNull(ostId);
        log.info("ostId: {}", ostId);
    }

    @Test
    @Transactional
    public void createOstTest_valid_map_add(){
        //given
        OstParam op = OstParam.builder().gameId(3l).ostNm("dron ost").mapId(25L).build();

        //when
        Long ostId = ostService.createOst(op);

        //then
        assertNotNull(ostId);
        log.info("ostId: {}", ostId);
    }

    @Test
    @Transactional
    public void createOstTest_valid_map_notExit(){
        //given
        OstParam op = OstParam.builder().gameId(3l).ostNm("marrin ost").mapId(123333L).build();

        //when
        Throwable exception = assertThrows(ParamValidException.class, () -> {
            ostService.createOst(op);
        });

        //then
        log.info("err msg: {}", exception.getMessage());
    }

    @Test
    @Transactional
    public void updateOstTest_valid_ostNm(){
        //given
        OstParam op = OstParam.builder().gameId(3l).ostId(232l).build();

        //when//then
        Throwable exception = assertThrows(ParamValidException.class, () -> {
            ostService.updateOst(op);
        });

        log.info("err msg: {}", exception.getMessage());
    }

    @Test
    @Transactional
    public void updateOstTest_valid_ostId(){
        //given
        OstParam op = OstParam.builder().gameId(3l).ostNm("zerg ost").build();

        //when//then
        Throwable exception = assertThrows(ParamValidException.class, () -> {
            ostService.updateOst(op);
        });

        log.info("err msg: {}", exception.getMessage());
    }

    @Test
    @Transactional
    public void updateOstTest_valid_gameId(){
        //given
        OstParam op = OstParam.builder().ostId(232l).ostNm("zerg ost").build();

        //when//then
        Throwable exception = assertThrows(ParamValidException.class, () -> {
            ostService.updateOst(op);
        });

        log.info("err msg: {}", exception.getMessage());
    }

    @Test
    @Transactional
    public void updateOstTest_valid_ostNm_overlap(){
        //given
        OstParam op = OstParam.builder().gameId(3l).ostId(232l).ostNm("protoss ost").build();

        //when//then
        Throwable exception = assertThrows(ParamValidException.class, () -> {
            ostService.updateOst(op);
        });

        log.info("err msg: {}", exception.getMessage());
    }

    @Test
    @Transactional
    public void updateOstTest_valid_ostId_notExist(){
        //given
        OstParam op = OstParam.builder().gameId(3l).ostId(1l).ostNm("protoss ost").build();

        //when//then
        Throwable exception = assertThrows(ParamValidException.class, () -> {
            ostService.updateOst(op);
        });

        log.info("err msg: {}", exception.getMessage());
    }

    @Test
    @Transactional
    public void updateOstTest_valid(){
        //given
        OstParam op = OstParam.builder().gameId(3l).ostId(263l).ostNm("protoss ost").build();

        //when
        Long ostId = ostService.updateOst(op);

        //then
        log.info("ostId: {}", ostId);
    }

    @Test
    @Transactional
    public void updateOstTest_valid_map_add(){
        //given
        OstParam op = OstParam.builder().gameId(3l).ostId(263l).ostNm("파일럿 ost").mapId(25l).build();

        //when
        Long ostId = ostService.updateOst(op);

        //then
        log.info("ostId: {}", ostId);
    }

    @Test
    @Transactional
    public void updateOstTest_valid_map_update(){
        //given
        OstParam op = OstParam.builder().gameId(3l).ostId(263l).ostNm("파일럿 ost").mapId(21l).build();

        //when
        Long ostId = ostService.updateOst(op);

        //then
        log.info("ostId: {}", ostId);
    }

    @Test
    @Transactional
    public void updateOstTest_valid_map_del(){
        //given
        OstParam op = OstParam.builder().gameId(3l).ostId(263l).ostNm("파일럿 ost").build();

        //when
        Long ostId = ostService.updateOst(op);

        //then
        log.info("ostId: {}", ostId);
    }

    @Test
    @Transactional
    public void pageOstTest(){
        //given
        OstSearch os = OstSearch.builder().gameId(3l).build();
        Pageable page = PageRequest.of(0, 10);

        //when
        Page<OstResponse> ost = ostService.pageOst(os, page);

        //then
        assertNotNull(ost);

        for(OstResponse or : ost){
            log.info("os: {}", or);
        }
    }

    @Test
    @Transactional
    public void detailOstTest(){
        //given
        OstSearch os = OstSearch.builder().ostId(263l).build();

        //when
        OstResponse or = ostService.detailOst(os);

        //then
        assertNotNull(os);

        log.info("ostReponse: {}", or);
    }

    @Test
    @Transactional
    public void delOstTest(){
        //given
        OstParam op = OstParam.builder().ostId(232l).build();

        //when
        ostService.delOst(op);

        //then
    }

    @Test
    @Transactional
    public void delOstsTest(){
        //given
        OstParam op = OstParam.builder().ostsId(Arrays.asList(265L, 232L)).build();
        //when
        ostService.delOst(op);
        //then
    }

}
