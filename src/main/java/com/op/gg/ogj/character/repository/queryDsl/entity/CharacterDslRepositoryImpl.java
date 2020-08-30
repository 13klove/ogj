package com.op.gg.ogj.character.repository.queryDsl.entity;

import com.op.gg.ogj.character.model.entity.Character;
import com.op.gg.ogj.item.model.Item;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static com.op.gg.ogj.character.model.entity.QCharacter.character;
import static com.op.gg.ogj.game.model.entity.QGame.game;
import static com.op.gg.ogj.item.model.QItem.item;
import static com.op.gg.ogj.itemSpec.model.QItemSpec.itemSpec;
import static com.op.gg.ogj.skill.model.QSkill.skill;


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

    @Override
    public Character findCharacterRelInfoByGameIdCharacterId(Long gameId, Long characterId) {
        Character result = jpaQueryFactory.select(character)
                .from(character)
                .leftJoin(character.skills, skill).fetchJoin()
                .where(whereCharacterId(characterId), gameWhereGameId(gameId))
                .fetchOne();

        result.getSkins();

        List<Item> items = jpaQueryFactory.select(item)
                .from(item)
                .join(item.itemSpec, itemSpec).fetchJoin()
                .where(itemWhereCharacterId(characterId), itemWhereActYn(true), itemSpecWhereActYn(true))
                .fetch();

        items.stream().forEach(result::smItemAdd);

        return result;
    }

    @Override
    public List<Character> findCharacterRelInfoByGameIdCharactersId(Long gameId, List<Long> charactersId) {
        List<Character> results = jpaQueryFactory.select(character)
                .from(character)
                .leftJoin(character.items, item)
                .leftJoin(item.itemSpec, itemSpec)
                .where(gameWhereGameId(gameId), whereCharactersId(charactersId), itemSpecWhereActYn(true), itemWhereActYn(true))
                .fetch();

        results.forEach(a->{a.getSkins(); a.getSkills();});
        return results;
    }

    public BooleanExpression whereCharacterId(Long characterId){ return characterId==null?null:character.characterId.eq(characterId); }

    public BooleanExpression whereCharactersId(List<Long> charactersId) { return charactersId==null||charactersId.isEmpty()?null:character.characterId.in(charactersId); }

    public BooleanExpression gameWhereGameId(Long gameId){
        return gameId==null?null:character.game.gameId.eq(gameId);
    }

    public BooleanExpression itemWhereCharacterId(Long characterId){ return characterId==null?null:item.character.characterId.eq(characterId); }

    public BooleanExpression itemWhereActYn(Boolean actYn){
        return actYn==null?null:item.actYn.eq(actYn);
    }

    public BooleanExpression itemSpecWhereActYn(Boolean actYn){
        return actYn==null?null: itemSpec.actYn.eq(actYn);
    }

}
