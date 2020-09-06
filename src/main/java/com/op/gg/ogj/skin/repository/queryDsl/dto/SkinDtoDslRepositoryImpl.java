package com.op.gg.ogj.skin.repository.queryDsl.dto;

import com.op.gg.ogj.skin.model.dto.QSkinResponse;
import com.op.gg.ogj.skin.model.dto.SkinResponse;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;

import static com.op.gg.ogj.skin.model.entity.QSkin.skin;

public class SkinDtoDslRepositoryImpl implements SkinDtoDslRepository {

    private JPAQueryFactory jpaQueryFactory;

    public SkinDtoDslRepositoryImpl(EntityManager entityManager){
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Page<SkinResponse> pageSkin(Long characterId, String skinNm, Pageable pageable) {
        QueryResults<SkinResponse> skinResponseQueryResults = jpaQueryFactory.select(new QSkinResponse(skin.skinId, skin.skinNm, skin.price))
                .from(skin)
                .where(whereCharacterId(characterId), whereSkinNm(skinNm), whereActYn(true))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        return new PageImpl<>(skinResponseQueryResults.getResults(), pageable, skinResponseQueryResults.getTotal());
    }

    @Override
    public SkinResponse detailSkin(Long skinId) {
        return jpaQueryFactory.select(new QSkinResponse(skin.skinId, skin.skinNm, skin.price))
                .from(skin)
                .where(whereSkinId(skinId))
                .fetchOne();
    }

    public BooleanExpression whereSkinId(Long skinId){
        return skinId==null?null:skin.skinId.eq(skinId);
    }

    public BooleanExpression whereCharacterId(Long characterId){
        return characterId==null?null:skin.character.characterId.eq(characterId);
    }

    public BooleanExpression whereSkinNm(String skinNm){
        return skinNm==null?null:skin.skinNm.eq(skinNm);
    }

    public BooleanExpression whereActYn(Boolean actYn){
        return actYn==null?null:skin.actYn.eq(actYn);
    }

}
