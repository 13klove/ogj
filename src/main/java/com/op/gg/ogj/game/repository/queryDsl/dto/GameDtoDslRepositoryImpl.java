package com.op.gg.ogj.game.repository.queryDsl.dto;

import com.op.gg.ogj.game.model.DeviceType;
import com.op.gg.ogj.game.model.GameFactoryMethod;
import com.op.gg.ogj.game.model.dto.GameResponse;
import com.op.gg.ogj.game.model.dto.GameSearch;
import com.op.gg.ogj.game.model.dto.QGameResponse;
import com.op.gg.ogj.map.model.dto.MapResponse;
import com.op.gg.ogj.map.model.dto.QMapResponse;
import com.op.gg.ogj.ost.model.dto.OstResponse;
import com.op.gg.ogj.ost.model.dto.QOstResponse;
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
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.op.gg.ogj.game.model.entity.QGame.game;
import static com.op.gg.ogj.gameInfo.model.entity.QGameInfo.gameInfo;
import static com.op.gg.ogj.map.model.entity.QMap.map;
import static com.op.gg.ogj.ost.model.entity.QOst.ost;


public class GameDtoDslRepositoryImpl implements GameDtoDslRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public GameDtoDslRepositoryImpl(EntityManager em){
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<GameResponse> findPageGameByGameParam(GameSearch gameSearch, Pageable pageable) {
        JPAQuery<GameResponse> query = jpaQueryFactory.select(new QGameResponse(game.gameId, game.gameNm, game.price, game.brand, game.deviceType, game.gameType, game.gameInfo.gameInfo1, game.gameInfo.gameInfo2))
                .from(game)
                .join(game.gameInfo, gameInfo)
                .where(whereGameNm(gameSearch.getGameNm()), whereGameType(gameSearch.getGameType())
                , whereDeviceType(gameSearch.getDeviceType()), wherePriceRange(gameSearch.getStartPrice(), gameSearch.getEndPrice())
                , whereBrand(gameSearch.getBrand()), whereCreateDateRange(gameSearch.getStartDate().atStartOfDay(), gameSearch.getEndDate().atStartOfDay()), whereActYn())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        if(pageable.getSort() != null) selectOrder(pageable.getSort(), query);

        QueryResults<GameResponse> result = query.fetchResults();

        return new PageImpl<>(result.getResults(), pageable, result.getTotal());
    }

    @Override
    public GameResponse findGameByGameId(GameSearch gameSearch) {
        GameResponse result = jpaQueryFactory.select(new QGameResponse(game.gameId, game.gameNm, game.price, game.brand, game.deviceType, game.gameType, game.gameInfo.gameInfo1, game.gameInfo.gameInfo2))
                .from(game)
                .join(game.gameInfo, gameInfo)
                .where(whereActYn(), whereGameId(gameSearch.getGameId()))
                .fetchOne();

        List<OstResponse> osts = jpaQueryFactory.select(new QOstResponse(ost.ostId, ost.ostNm))
                .from(ost)
                .where(ostWhereGameId(gameSearch.getGameId()), ostWhereActYn())
                .fetch();

        List<Long> mapsId = osts.stream().map(a->a.getMapId()).collect(Collectors.toList());

        List<MapResponse> maps = jpaQueryFactory.select(new QMapResponse((map.mapNm)))
                .from(map)
                .where(mapWhereOstId(mapsId), mapWhereActYn())
                .fetch();

        result.setOsts(osts);
        result.setMaps(maps);

        return result;
    }

    private void selectOrder(Sort sort, JPAQuery<GameResponse> query){
        for (Sort.Order order : sort) {
            Order direction = order.isAscending() ? Order.ASC : Order.DESC;
            switch (order.getProperty()) {
                case "gameNm":
                    query.orderBy(new OrderSpecifier(direction, game.gameNm));
                    break;
                case "gameType":
                    query.orderBy(new OrderSpecifier(direction, game.gameType));
                    break;
                case "deviceType":
                    query.orderBy(new OrderSpecifier(direction, game.deviceType));
                    break;
                case "price":
                    query.orderBy(new OrderSpecifier(direction, game.price));
                    break;
                case "brand":
                    query.orderBy(new OrderSpecifier(direction, game.brand));
                    break;
                case "createDt":
                    query.orderBy(new OrderSpecifier(direction, game.createDt));
                    break;
            }
        }
    }

    private BooleanExpression whereGameId(Long gameId) { return gameId != null?game.gameId.eq(gameId):null; }

    private BooleanExpression whereGameNm(String gameNm) { return StringUtils.hasText(gameNm)?game.gameNm.eq(gameNm):null; }

    private BooleanExpression whereGameType(GameFactoryMethod gameType) { return gameType != null?game.gameType.eq(gameType):null; }

    private BooleanExpression whereDeviceType(DeviceType deviceType) { return deviceType != null?game.deviceType.eq(deviceType):null; }

    private BooleanExpression wherePriceRange(Integer startPrice, Integer endPrice) { return startPrice != null?game.price.between(startPrice, endPrice):null; }

    private BooleanExpression whereBrand(String brand) { return StringUtils.hasText(brand)?game.brand.eq(brand):null; }

    private BooleanExpression whereCreateDateRange(LocalDateTime startDate, LocalDateTime endDate) { return startDate != null?game.createDt.between(startDate, endDate):null; }

    private BooleanExpression whereActYn() { return game.actYn.eq(true); }

    private BooleanExpression ostWhereGameId(Long gameId) { return gameId != null?ost.game.gameId.eq(gameId):null; }

    private BooleanExpression ostWhereActYn() { return ost.actYn.eq(true); }

    private BooleanExpression mapWhereOstId(List<Long> mapId) { return mapId != null&&!mapId.isEmpty()?map.mapId.in(mapId):null; }

    private BooleanExpression mapWhereActYn() { return map.actYn.eq(true); }

}
