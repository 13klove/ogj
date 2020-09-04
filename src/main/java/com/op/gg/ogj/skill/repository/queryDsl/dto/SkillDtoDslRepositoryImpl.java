package com.op.gg.ogj.skill.repository.queryDsl.dto;

import com.op.gg.ogj.skill.model.dto.QSkillResponse;
import com.op.gg.ogj.skill.model.dto.SkillResponse;
import com.op.gg.ogj.skill.model.dto.SkillSearch;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static com.op.gg.ogj.skill.model.entity.QSkill.skill;

public class SkillDtoDslRepositoryImpl implements SkillDtoDslRepository{

    private JPAQueryFactory jpaQueryFactory;

    public SkillDtoDslRepositoryImpl(EntityManager entityManager){
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<SkillResponse> pageSkill(SkillSearch skillSearch) {
        return jpaQueryFactory.select(new QSkillResponse(skill.skillId, skill.character.characterId
                , skill.skillNm, skill.useEnergy, skill.useLife, skill.damage, skill.skillType, skill.ulimateYn, skill.imgUrl))
                .from(skill)
                .where(whereCharacterId(skillSearch.getCharacterId()), whereActYn(true))
                .fetch();
    }

    @Override
    public SkillResponse findSkillBySkillId(Long skillId) {
        return jpaQueryFactory.select(new QSkillResponse(skill.skillId, skill.character.characterId
                , skill.skillNm, skill.useEnergy, skill.useLife, skill.damage, skill.skillType, skill.ulimateYn, skill.imgUrl))
                .from(skill)
                .where(whereSkillId(skillId), whereActYn(true))
                .fetchOne();
    }

    public BooleanExpression whereSkillId(Long skillId){
        return skillId==null?null:skill.skillId.eq(skillId);
    }

    public BooleanExpression whereCharacterId(Long characterId){
        return characterId==null?null:skill.character.characterId.eq(characterId);
    }

    public BooleanExpression whereActYn(Boolean actYn){
        return actYn==null?null:skill.actYn.eq(actYn);
    }

}
