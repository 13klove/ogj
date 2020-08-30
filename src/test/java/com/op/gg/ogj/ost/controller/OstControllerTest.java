package com.op.gg.ogj.ost.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.op.gg.ogj.ost.model.dto.OstParam;
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
public class OstControllerTest {

    @Autowired
    MockMvc moc;
    ObjectMapper om = new ObjectMapper();

    @Test
    @Transactional
    public void createOstTest() throws Exception{
        OstParam op = OstParam.builder().gameId(7l).ostNm("칼바람 ost").mapId(22L).build();
        String str = om.writeValueAsString(op);

        moc.perform(post("/api/ost").content(str).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    public void updateOstTest() throws Exception{
        OstParam op = OstParam.builder().gameId(3l).ostId(265l).ostNm("Asite ost").mapId(25l).build();
        String str = om.writeValueAsString(op);

        moc.perform(put("/api/ost/{id}", 265).content(str).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    public void pageOstTest() throws Exception{
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("gameId", "3");
        params.add("page", "0");
        params.add("size", "10");

        moc.perform(get("/api/ost").params(params))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    public void detailOstTest() throws Exception{
        moc.perform(get("/api/ost/{id}", 265))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    public void delOstTest() throws Exception{
        moc.perform(put("/api/ost/{id}/del", 232))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    public void delOstsTest() throws Exception{
        OstParam op = OstParam.builder().ostsId(Arrays.asList(232l, 266l)).build();
        String str = om.writeValueAsString(op);

        moc.perform(post("/api/ost/del").content(str).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
