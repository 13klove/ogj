package com.op.gg.ogj.character.repository.queryDsl.entity;

import com.op.gg.ogj.character.model.entity.Character;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;

import static com.op.gg.ogj.character.model.entity.QCharacter.character;
import static com.op.gg.ogj.game.model.entity.QGame.game;


public class CharacterDslRepositoryImpl implements CharacterDslRepository{

    private final JPAQueryFactory jpaQueryFactory;

    public CharacterDslRepositoryImpl(EntityManager entityManager){
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Character findCharacterByCharacterId(Long characterId) {
        return jpaQueryFactory.selectFrom(character)
                .join(character.game, game).fetchJoin()
                .where(whereCharacterId(characterId))
                .fetchOne();
    }

    public BooleanExpression whereCharacterId(Long characterId){
        return characterId==null?null:character.characterId.eq(characterId);
    }

}
