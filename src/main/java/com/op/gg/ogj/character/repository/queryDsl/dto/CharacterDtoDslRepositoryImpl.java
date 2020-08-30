package com.op.gg.ogj.character.repository.queryDsl.dto;

import com.op.gg.ogj.character.model.CharacterType;
import com.op.gg.ogj.character.model.dto.CharacterResponse;
import com.op.gg.ogj.character.model.dto.QCharacterResponse;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;

import static com.op.gg.ogj.character.model.entity.QCharacter.character;

public class CharacterDtoDslRepositoryImpl implements CharacterDtoDslRepository{

    private final JPAQueryFactory jpaQueryFactory;

    public CharacterDtoDslRepositoryImpl(EntityManager entityManager){
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Page<CharacterResponse> pageCharacter(Long gameId, String characterNm, CharacterType characterType, Pageable pageable) {
        QueryResults<CharacterResponse> result = jpaQueryFactory.select(new QCharacterResponse(character.game.gameId, character.characterId, character.characterNm, character.characterType, character.life, character.energy, character.imgUrl))
                .from(character)
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .where(gameWhereGameId(gameId), whereCharacterNm(characterNm), whereCharacterType(characterType), whereActYn(true))
                .fetchResults();
        return new PageImpl<>(result.getResults(), pageable, result.getTotal());
    }

    @Override
    public CharacterResponse detailCharacterByCharacterId(Long characterId) {
        return jpaQueryFactory.select(new QCharacterResponse(character.game.gameId, character.characterId, character.characterNm, character.characterType, character.life, character.energy, character.imgUrl))
                .from(character)
                .where(whereCharacterId(characterId), whereActYn(true))
                .fetchOne();
    }

    public BooleanExpression whereCharacterId(Long characterId){
        return characterId==null?null:character.characterId.eq(characterId);
    }

    public BooleanExpression whereCharacterNm(String characterNm){
        return characterNm==null?null:character.characterNm.eq(characterNm);
    }

    public BooleanExpression whereCharacterType(CharacterType characterType){
        return characterType==null?null:character.characterType.eq(characterType);
    }

    public BooleanExpression whereActYn(Boolean actYn){
        return actYn==null?null:character.actYn.eq(actYn);
    }

    public BooleanExpression gameWhereGameId(Long gameId){
        return gameId==null?null:character.game.gameId.eq(gameId);
    }

}
