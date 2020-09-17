package com.op.gg.ogj.skin.repository.queryDsl.entity;

import com.op.gg.ogj.skin.model.entity.Skin;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;

import java.util.List;

import static com.op.gg.ogj.character.model.entity.QCharacter.character;
import static com.op.gg.ogj.skin.model.entity.QSkin.skin;

public class SkinDslRepositoryImpl implements SkinDslRepository{

    private JPAQueryFactory jpaQueryFactory;

    public SkinDslRepositoryImpl(EntityManager entityManager){
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Skin findSkinAndCharacterBySkinId(Long skinId) {
        return jpaQueryFactory.selectFrom(skin)
                .join(skin.character, character).fetchJoin()
                .where(whereSkinId(skinId), whereActYn(true))
                .fetchOne();
    }

    @Override
    public List<Skin> findSkinAndCharacterBySkinIds(List<Long> skinIds) {
        return jpaQueryFactory.selectFrom(skin)
                .join(skin.character, character).fetchJoin()
                .where(whereSkinIds(skinIds), whereActYn(true))
                .fetch();
    }

    @Override
    public void updateSkinActYn(List<Long> characterIds) {
        jpaQueryFactory.update(skin)
                .set(skin.actYn, false)
                .where(whereInCharacterId(characterIds))
                .execute();
    }

    private BooleanExpression whereInCharacterId(List<Long> characterIds) { return characterIds==null||characterIds.isEmpty()?null:skin.character.characterId.in(characterIds); }

    public BooleanExpression whereSkinId(Long skinId){
        return skinId==null?null:skin.skinId.eq(skinId);
    }

    public BooleanExpression whereSkinIds(List<Long> skinIds){ return skinIds==null||skinIds.isEmpty()?null:skin.skinId.in(skinIds); }

    public BooleanExpression whereActYn(Boolean actYn){
        return actYn==null?null:skin.actYn.eq(actYn);
    }

}
