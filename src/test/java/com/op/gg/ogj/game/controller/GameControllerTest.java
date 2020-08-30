package com.op.gg.ogj.game.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.op.gg.ogj.game.model.DeviceType;
import com.op.gg.ogj.game.model.GameFactoryMethod;
import com.op.gg.ogj.game.model.dto.GameParam;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @Transactional
    public void createGameTest() throws Exception{
        GameParam gameParam = GameParam.builder().gameNm("테스트게임23").gameInfo1("테스트게임정보1").brand("인텔")
                .deviceType(DeviceType.PC).price(9900).gameType(GameFactoryMethod.AOS).build();
        String param = objectMapper.writeValueAsString(gameParam);

        mockMvc.perform(post("/api/game")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(param))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Transactional
    public void updateGameTest() throws Exception{
        GameParam gameParam = GameParam.builder().gameNm("테스트게임2").gameInfo1("테스트게임정보1수정").brand("인텔에서이클립스로")
                .deviceType(DeviceType.PC).price(9900).gameType(GameFactoryMethod.AOS).build();
        String param = objectMapper.writeValueAsString(gameParam);

        mockMvc.perform(put("/api/game/{id}", 9)
                .content(param)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Transactional
    public void pageGameTest() throws Exception{
        MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
        param.add("startDate", "20200814");
        param.add("endDate", "20200816");
        param.add("gameNm", "테스트");
        param.add("page", "0");
        param.add("size", "20");
        param.add("sort", "gameNm,desc");
        mockMvc.perform(get("/api/game").params(param))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Transactional
    public void detailGameTest() throws Exception{
        mockMvc.perform(get("/api/game/{id}", 13))
                .andExpect(status().isOk())
                .andDo(print());
    }

}
