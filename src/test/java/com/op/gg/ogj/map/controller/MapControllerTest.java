package com.op.gg.ogj.map.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.op.gg.ogj.map.model.dto.MapParam;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MapControllerTest {

    @Autowired
    MockMvc mm;
    ObjectMapper om = new ObjectMapper();

    @Test
    @Transactional
    public void createMapTest()throws Exception{
        MapParam mp = MapParam.builder().mapNm("컨트롤타워").gameId(13l).build();
        String str = om.writeValueAsString(mp);

        mm.perform(post("/api/map").content(str).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    public void updateMapTest()throws Exception{
        MapParam mp = MapParam.builder().gameId(13l).mapNm("바람의나라_모바일").build();
        String str = om.writeValueAsString(mp);

        mm.perform(put("/api/map/{id}", 30).content(str).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    public void pageMapTest()throws Exception{
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("gameId", "7");
        params.add("page", "0");
        params.add("size", "10");
        params.add("sort", "mapNm,desc");

        mm.perform(get("/api/map").params(params))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    public void detailMapTest()throws Exception{
        mm.perform(get("/api/map/{id}", 30))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    public void delMapTest()throws Exception{
        mm.perform(put("/api/map/del/{id}", 31))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    public void delMapsTest()throws Exception{
        MapParam mp = MapParam.builder().mapsId(Arrays.asList(32l, 33l)).build();
        String str = om.writeValueAsString(mp);

        mm.perform(post("/api/map/del").content(str).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
