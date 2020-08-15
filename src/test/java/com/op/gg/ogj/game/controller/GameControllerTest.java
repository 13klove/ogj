package com.op.gg.ogj.game.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private static ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void gameInsertTest() throws Exception{
//        GameParam gameParam = GameParam.builder().build();
//        String param = objectMapper.writeValueAsString(gameParam);
//
//        mockMvc.perform(post("/game")
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .content(param))
//                .andExpect(status().isOk())
//                .andDo(print());
    }

}
