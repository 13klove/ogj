package com.op.gg.ogj.character.controller;

import com.op.gg.ogj.character.model.CharacterType;
import com.op.gg.ogj.character.model.dto.CharacterParam;
import com.op.gg.ogj.common.CommonController;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CharacterControllerTest extends CommonController {

    @Test
    @Transactional
    public void createCharacterTest() throws Exception{
        CharacterParam cp = CharacterParam.builder().gameId(3l).characterNm("벌처").life(120).energy(0).characterType(CharacterType.ATTACK).build();
        String str = om.writeValueAsString(cp);

        mm.perform(post("/api/character").content(str).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    public void updateCharacterTest() throws Exception{
        CharacterParam cp = CharacterParam.builder().gameId(3l).characterNm("시즈탱크").life(230).energy(0).characterType(CharacterType.ATTACK).build();
        String str = om.writeValueAsString(cp);

        mm.perform(put("/api/character/{id}", 362).content(str).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    public void pageCharacterTest() throws Exception{
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("gameId", "3");
        params.add("page", "0");
        params.add("size", "10");

        mm.perform(get("/api/character").params(params))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    public void detailCharacterTest() throws Exception{
        mm.perform(get("/api/character/{id}", 359))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    public void delCharacterTest() throws Exception{
        CharacterParam cp = CharacterParam.builder().gameId(3l).build();
        String str = om.writeValueAsString(cp);

        mm.perform(put("/api/character/{id}/del", 359).content(str).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    public void delCharactersTest() throws Exception{
        CharacterParam cp = CharacterParam.builder().gameId(3l).charactersId(Arrays.asList(362l, 359l)).build();
        String str = om.writeValueAsString(cp);

        mm.perform(post("/api/character/del").content(str).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
