package com.op.gg.ogj.character.service;

import com.op.gg.ogj.character.model.CharacterType;
import com.op.gg.ogj.character.model.dto.CharacterParam;
import com.op.gg.ogj.character.model.dto.CharacterResponse;
import com.op.gg.ogj.character.model.dto.CharacterSearch;
import com.op.gg.ogj.config.exception.domain.ParamValidException;
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
public class CharacterServiceTest {

    @Autowired
    private CharacterService characterService;

    @Test
    @Transactional
    public void createCharacterTest_gameId(){
        CharacterParam cp = CharacterParam.builder().build();

        Throwable exception = assertThrows(ParamValidException.class, ()->{
           characterService.createCharacter(cp);
        });

        log.info("error msg: {}", exception.getMessage());
    }

    @Test
    @Transactional
    public void createCharacterTest_gameId_no_have(){
        CharacterParam cp = CharacterParam.builder().gameId(999999999l).build();

        Throwable exception = assertThrows(ParamValidException.class, ()->{
           characterService.createCharacter(cp);
        });

        log.info("error msg: {}", exception.getMessage());
    }

    @Test
    @Transactional
    public void createCharacterTest_characterType(){
        CharacterParam cp = CharacterParam.builder().gameId(3l).build();

        Throwable exception = assertThrows(ParamValidException.class, ()->{
            characterService.createCharacter(cp);
        });

        log.info("error msg: {}", exception.getMessage());
    }

    @Test
    @Transactional
    public void createCharacterTest_life(){
        CharacterParam cp = CharacterParam.builder().gameId(3l).characterType(CharacterType.ATTACK).build();

        Throwable exception = assertThrows(ParamValidException.class, ()->{
            characterService.createCharacter(cp);
        });

        log.info("error msg: {}", exception.getMessage());
    }

    @Test
    @Transactional
    public void createCharacterTest_energy(){
        CharacterParam cp = CharacterParam.builder().gameId(3l)
                .characterType(CharacterType.ATTACK).life(50).build();

        Throwable exception = assertThrows(ParamValidException.class, ()->{
            characterService.createCharacter(cp);
        });

        log.info("error msg: {}", exception.getMessage());
    }

    @Test
    @Transactional
    public void createCharacterTest_characterNm(){
        CharacterParam cp = CharacterParam.builder().gameId(3l)
                .characterType(CharacterType.ATTACK).life(50).energy(150).build();

        Throwable exception = assertThrows(ParamValidException.class, ()->{
            characterService.createCharacter(cp);
        });

        log.info("error msg: {}", exception.getMessage());
    }

    @Test
    @Transactional
    public void createCharacterTest_exit_character(){
        CharacterParam cp = CharacterParam.builder().gameId(3l)
                .characterType(CharacterType.ATTACK).life(50).energy(150)
                .characterNm("고스트").build();

        Throwable exception = assertThrows(ParamValidException.class, ()->{
            characterService.createCharacter(cp);
        });

        log.info("error msg: {}", exception.getMessage());
    }

    @Test
    @Transactional
    public void createCharacterTest(){
        CharacterParam cp = CharacterParam.builder().gameId(3l)
                .characterType(CharacterType.ATTACK).life(50).energy(150)
                .characterNm("메딕").build();

        Long characterId = characterService.createCharacter(cp);

        log.info("character: {}", characterId);
        assertNotNull(characterId);
    }

    @Test
    @Transactional
    public void updateCharacterTest(){
        CharacterParam cp = CharacterParam.builder().gameId(3l).characterId(359l)
                .characterType(CharacterType.ATTACK).life(80).energy(0)
                .characterNm("고스트").build();

        Long characterId = characterService.updateCharacter(cp);

        log.info("character: {}", characterId);
        assertNotNull(characterId);
    }

    @Test
    @Transactional
    public void updateCharacterTest_gameId(){
        CharacterParam cp = CharacterParam.builder().build();

        Throwable exception = assertThrows(ParamValidException.class, ()->{
            characterService.updateCharacter(cp);
        });

        log.info("error msg: {}", exception.getMessage());
    }

    @Test
    @Transactional
    public void updateCharacterTest_characterId(){
        CharacterParam cp = CharacterParam.builder().gameId(3l).build();

        Throwable exception = assertThrows(ParamValidException.class, ()->{
            characterService.updateCharacter(cp);
        });

        log.info("error msg: {}", exception.getMessage());
    }

    @Test
    @Transactional
    public void updateCharacterTest_characterType(){
        CharacterParam cp = CharacterParam.builder().gameId(3l).characterId(362l).build();

        Throwable exception = assertThrows(ParamValidException.class, ()->{
            characterService.updateCharacter(cp);
        });

        log.info("error msg: {}", exception.getMessage());
    }

    @Test
    @Transactional
    public void updateCharacterTest_life(){
        CharacterParam cp = CharacterParam.builder().gameId(3l).characterId(362l)
                .characterType(CharacterType.ATTACK).build();

        Throwable exception = assertThrows(ParamValidException.class, ()->{
            characterService.updateCharacter(cp);
        });

        log.info("error msg: {}", exception.getMessage());
    }

    @Test
    @Transactional
    public void updateCharacterTest_energy(){
        CharacterParam cp = CharacterParam.builder().gameId(3l).characterId(362l)
                .characterType(CharacterType.ATTACK).life(95).build();

        Throwable exception = assertThrows(ParamValidException.class, ()->{
            characterService.updateCharacter(cp);
        });

        log.info("error msg: {}", exception.getMessage());
    }

    @Test
    @Transactional
    public void updateCharacterTest_characterNm(){
        CharacterParam cp = CharacterParam.builder().gameId(3l).characterId(362l)
                .characterType(CharacterType.ATTACK).life(95).energy(50).build();

        Throwable exception = assertThrows(ParamValidException.class, ()->{
            characterService.updateCharacter(cp);
        });

        log.info("error msg: {}", exception.getMessage());
    }

    @Test
    @Transactional
    public void updateCharacterTest_exit_character(){
        CharacterParam cp = CharacterParam.builder().gameId(3l).characterId(362l)
                .characterType(CharacterType.ATTACK).life(95).energy(50)
                .characterNm("고스트").build();

        Throwable exception = assertThrows(ParamValidException.class, ()->{
            characterService.updateCharacter(cp);
        });

        log.info("error msg: {}", exception.getMessage());
    }

    @Test
    @Transactional
    public void pageCharacterTest(){
        CharacterSearch cs = CharacterSearch.builder().gameId(3l).build();
        Pageable page = PageRequest.of(0, 10);

        Page<CharacterResponse> result = characterService.pageCharacter(cs, page);

        for(CharacterResponse temp : result){
            log.info("page {}", temp);
        }

        assertNotNull(result);
    }

    @Test
    @Transactional
    public void pageCharacterTest_gameId(){
        CharacterSearch cs = CharacterSearch.builder().build();
        Pageable page = PageRequest.of(0, 10);

        Throwable exception = assertThrows(ParamValidException.class, ()->{
            characterService.pageCharacter(cs, page);
        });

        log.info("error msg: {}", exception.getMessage());
    }

    @Test
    @Transactional
    public void detailCharacterTest(){
        CharacterSearch cs = CharacterSearch.builder().characterId(359l).build();

        CharacterResponse cr = characterService.detailCharacter(cs);

        log.info("character: {}", cr);

        assertNotNull(cr);
    }

    @Test
    @Transactional
    public void detailCharacterTest_characterId(){
        CharacterSearch cs = CharacterSearch.builder().build();

        Throwable exception = assertThrows(ParamValidException.class, ()->{
            CharacterResponse cr = characterService.detailCharacter(cs);
        });

        log.info("error msg: {}", exception.getMessage());

    }

    @Test
    @Transactional
    public void delCharacterTest_gameId(){
        CharacterParam cp = CharacterParam.builder().build();

        Throwable exception = assertThrows(ParamValidException.class, ()->{
            characterService.delCharacter(cp);
        });

        log.info("error msg: {}", exception.getMessage());
    }

    @Test
    @Transactional
    public void delCharacterTest_characterId(){
        CharacterParam cp = CharacterParam.builder().gameId(3l).build();

        Throwable exception = assertThrows(ParamValidException.class, ()->{
            characterService.delCharacter(cp);
        });

        log.info("error msg: {}", exception.getMessage());
    }

    @Test
    @Transactional
    public void delCharacterTest_character_no_have(){
        CharacterParam cp = CharacterParam.builder().gameId(3l).characterId(33l).build();

        Throwable exception = assertThrows(ParamValidException.class, ()->{
            characterService.delCharacter(cp);
        });

        log.info("error msg: {}", exception.getMessage());
    }

    @Test
    @Transactional
    public void delCharacterTest(){
        CharacterParam cp = CharacterParam.builder().gameId(3l).characterId(359l).build();

        characterService.delCharacter(cp);
    }

    @Test
    @Transactional
    public void delCharactersTest_gameId(){
        CharacterParam cp = CharacterParam.builder().build();

        Throwable exception = assertThrows(ParamValidException.class, ()->{
            characterService.delCharacters(cp);
        });

        log.info("error msg: {}", exception.getMessage());
    }

    @Test
    @Transactional
    public void delCharactersTest_charactersId(){
        CharacterParam cp = CharacterParam.builder().gameId(3l).build();

        Throwable exception = assertThrows(ParamValidException.class, ()->{
            characterService.delCharacters(cp);
        });

        log.info("error msg: {}", exception.getMessage());
    }

    @Test
    @Transactional
    public void delCharactersTest(){
        CharacterParam cp = CharacterParam.builder().gameId(3l).charactersId(Arrays.asList(359l, 362l)).build();

        characterService.delCharacters(cp);
    }

}
