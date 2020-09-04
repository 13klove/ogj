package com.op.gg.ogj.skill.service;

import com.google.common.collect.Lists;
import com.op.gg.ogj.config.exception.domain.ParamValidException;
import com.op.gg.ogj.skill.model.SkillType;
import com.op.gg.ogj.skill.model.dto.SkillParam;
import com.op.gg.ogj.skill.model.dto.SkillResponse;
import com.op.gg.ogj.skill.model.dto.SkillSearch;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
@SpringBootTest
public class SkillServiceTest {

    @Autowired
    SkillService skillService;
    @Autowired
    EntityManager entityManager;
    Long tempId1;
    Long tempId2;
    List<Long> tempsId;

    @BeforeEach
    @Transactional
    public void dumpData(){
        SkillParam skillParam = SkillParam.builder().characterId(359l).skillNm("핵투하").useEnergy(15).useLife(80).skillType(SkillType.HEAL)
                .damage(0).ulimateYn(false).build();

        SkillParam skillParam2 = SkillParam.builder().characterId(359l).skillNm("저격").useEnergy(30).useLife(0).skillType(SkillType.ATTACK)
                .damage(0).ulimateYn(false).build();

        tempsId = Lists.newArrayList();

        tempId1 = skillService.createSkill(skillParam);
        tempId2 = skillService.createSkill(skillParam2);

        tempsId.add(tempId1);
        tempsId.add(tempId2);
    }

    @Test
    @Transactional
    public void createSkillTest_characterId(){
        SkillParam skillParam = SkillParam.builder().build();

        Throwable exception = assertThrows(ParamValidException.class, () ->{
            skillService.createSkill(skillParam);
        });

        log.info("exception: {}", exception.getMessage());
    }

    @Test
    @Transactional
    public void createSkillTest_skillName(){
        SkillParam skillParam = SkillParam.builder().characterId(359l).build();

        Throwable exception = assertThrows(ParamValidException.class, () ->{
            skillService.createSkill(skillParam);
        });

        log.info("exception: {}", exception.getMessage());
    }

    @Test
    @Transactional
    public void createSkillTest(){
        SkillParam skillParam = SkillParam.builder().characterId(359l).skillNm("스팀팩").useEnergy(15).useLife(80).skillType(SkillType.HEAL)
                .damage(0).ulimateYn(false).build();

        Long id = skillService.createSkill(skillParam);

        assertNotNull(id);
        log.info("id: {}", id);
    }

    @Test
    @Transactional
    public void updateSkillTest(){
        SkillParam skillParam = SkillParam.builder().characterId(359l).skillId(tempId1).skillNm("뉴클리어").useEnergy(15).useLife(80).skillType(SkillType.HEAL)
                .damage(0).ulimateYn(false).build();

        Long id = skillService.updateSkill(skillParam);
        entityManager.flush();

        assertNotNull(id);
        log.info("id: {}", id);
    }

    @Test
    @Transactional
    public void listSkillTest(){
        SkillSearch skillSearch = SkillSearch.builder().characterId(359l).build();

        List<SkillResponse> listSkill = skillService.listSkill(skillSearch);

        assertNotNull(listSkill);
        listSkill.forEach(a->log.info("skillResponse: {}", a));
    }

    @Test
    @Transactional
    public void detailSkillTest(){
        SkillSearch skillSearch = SkillSearch.builder().skillId(tempId1).build();

        SkillResponse skillResponse = skillService.detailSkill(skillSearch);

        assertNotNull(skillResponse);
        log.info("skillResponse: {}", skillResponse);
    }

    @Test
    @Transactional
    public void delSkillTest(){
        SkillParam skillParam = SkillParam.builder().skillId(tempId1).build();
        skillService.delSkill(skillParam);
        entityManager.flush();
    }

    @Test
    @Transactional
    public void delSkillsTest(){
        SkillParam skillParam = SkillParam.builder().skillsId(tempsId).build();
        skillService.delSkills(skillParam);
        entityManager.flush();
    }

}
