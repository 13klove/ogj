package com.op.gg.ogj.ost.repository.queryDsl.dto;

import com.op.gg.ogj.ost.model.dto.OstResponse;
import com.op.gg.ogj.ost.model.dto.OstSearch;
import com.op.gg.ogj.ost.model.dto.QOstResponse;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;

import static com.op.gg.ogj.game.model.entity.QGame.game;
import static com.op.gg.ogj.map.model.entity.QMap.map;
import static com.op.gg.ogj.ost.model.entity.QOst.ost;

public class OstDtoDslRepositoryImpl implements OstDtoDslRepository{

    private final JPAQueryFactory jpaQueryFactory;

    public OstDtoDslRepositoryImpl(EntityManager em){
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<OstResponse> findPageOst(OstSearch ostSearch, Pageable pageable) {
        QueryResults<OstResponse> results = jpaQueryFactory.select(new QOstResponse(ost.ostId, ost.ostNm, ost.map.mapId, ost.map.mapNm, ost.game.gameId))
                .from(ost)
                .join(ost.game, game)
                .leftJoin(ost.map, map)
                .where(whereOstNm(ostSearch.getOstNm()), gameWhereGameId(ostSearch.getGameId()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        return new PageImpl(results.getResults(), pageable, results.getTotal());
    }

    @Override
    public OstResponse findDetailOst(OstSearch ostSearch) {
        return jpaQueryFactory.select(new QOstResponse(ost.ostId, ost.ostNm, ost.map.mapId, ost.map.mapNm))
                .from(ost)
                .leftJoin(ost.map, map)
                .where(whereOstId(ostSearch.getOstId()))
                .fetchOne();
    }

    private BooleanExpression whereOstNm(String ostNm){
        return ostNm == null?null:ost.ostNm.eq(ostNm);
    }

    private BooleanExpression whereOstId(Long ostId){
        return ostId == null?null:ost.ostId.eq(ostId);
    }

    private BooleanExpression gameWhereGameId(Long gameId){
        return gameId == null?null:ost.game.gameId.eq(gameId);
    }

}
