package com.op.gg.ogj.map.repository.queryDsl.dto;

import com.op.gg.ogj.map.model.dto.MapResponse;
import com.op.gg.ogj.map.model.dto.MapSearch;
import com.op.gg.ogj.map.model.dto.QMapResponse;
import com.op.gg.ogj.map.model.entity.Map;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;

import static com.op.gg.ogj.map.model.entity.QMap.map;
import static com.op.gg.ogj.ost.model.entity.QOst.ost;

public class MapDtoDslRepositoryImpl implements MapDtoDslRepository{

    private JPAQueryFactory jpaQueryFactory;

    public MapDtoDslRepositoryImpl(EntityManager em){
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<MapResponse> findPageMap(MapSearch mapSearch, Pageable pageable) {
        JPAQuery<MapResponse> query = jpaQueryFactory.select(new QMapResponse(map.mapId, map.mapNm))
                .from(map)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .where(whereMapNm(mapSearch.getMapNm()), whereGameId(mapSearch.getGameId()));

        if(pageable.getSort() != null) selectOrder(pageable.getSort(), query);

        QueryResults<MapResponse> result = query.fetchResults();

        return new PageImpl(result.getResults(), pageable, result.getTotal());
    }

    @Override
    //여기서는 joinfetch로 가져오고 vo로 변환 한다.
    public MapResponse findDetailMapByMapId(MapSearch mapSearch) {
        Map dbMap = jpaQueryFactory.select(map)
                .distinct()
                .from(map)
                .leftJoin(map.osts, ost).fetchJoin()
                .where(whereMapId(mapSearch.getMapId()))
                .fetchOne();

        return new MapResponse(dbMap);
    }

    private void selectOrder(Sort sort, JPAQuery<MapResponse> query){
        for (Sort.Order order : sort) {
            Order direction = order.isAscending() ? Order.ASC : Order.DESC;
            switch (order.getProperty()) {
                case "mapNm":
                    query.orderBy(new OrderSpecifier(direction, map.mapNm));
                    break;
            }
        }
    }

    public BooleanExpression whereMapNm(String mapNm){
        return StringUtils.hasText(mapNm)?map.mapNm.eq(mapNm):null;
    }

    public BooleanExpression whereMapId(Long mapId){
        return mapId!=null?map.mapId.eq(mapId):null;
    }

    public BooleanExpression whereGameId(Long gameId){ return gameId!=null?map.game.gameId.eq(gameId):null; }
}
