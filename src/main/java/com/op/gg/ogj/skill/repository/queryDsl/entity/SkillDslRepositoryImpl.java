package com.op.gg.ogj.skill.repository.queryDsl.entity;

import com.op.gg.ogj.skill.model.entity.Skill;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;

import java.util.List;

import static com.op.gg.ogj.character.model.entity.QCharacter.character;
import static com.op.gg.ogj.skill.model.entity.QSkill.skill;

public class SkillDslRepositoryImpl implements SkillDslRepository{

    private JPAQueryFactory jpaQueryFactory;

    public SkillDslRepositoryImpl(EntityManager entityManager){
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Skill findSkillAndCharacterBySkillId(Long skillId) {
        return jpaQueryFactory.select(skill)
                .from(skill)
                .join(skill.character, character).fetchJoin()
                .where(whereSkillId(skillId), whereActYn(true))
                .fetchOne();
    }

    @Override
    public List<Skill> findSkillsBySkillsId(List<Long> skillsId) {
        return jpaQueryFactory.select(skill)
                .from(skill)
                .join(skill.character, character).fetchJoin()
                .where(whereActYn(true), whereSkillsId(skillsId))
                .fetch();
    }

    @Override
    public void updateSkillActYn(List<Long> characterIds) {
        jpaQueryFactory.update(skill)
                .set(skill.actYn, false)
                .where(whereInCharacterIds(characterIds))
                .execute();
    }

    private BooleanExpression whereInCharacterIds(List<Long> characterIds) { return characterIds==null||characterIds.isEmpty()?null:skill.character.characterId.in(characterIds); }

    private Predicate whereSkillsId(List<Long> skillsId) { return skillsId==null||skillsId.isEmpty()?null:skill.skillId.in(skillsId); }

    public BooleanExpression whereSkillId(Long skillId){
        return skillId==null?null:skill.skillId.eq(skillId);
    }

    public BooleanExpression whereActYn(Boolean actYn){
        return actYn==null?null:skill.actYn.eq(actYn);
    }

}
