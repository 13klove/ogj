package com.op.gg.ogj.game.repository.queryDsl;

import com.op.gg.ogj.game.model.DeviceType;
import com.op.gg.ogj.game.model.GameFactoryMethod;
import com.op.gg.ogj.game.model.dto.GameResponse;
import com.op.gg.ogj.game.model.dto.GameSearch;
import com.op.gg.ogj.game.model.dto.QGameResponse;
import com.op.gg.ogj.game.model.entity.Game;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;

import static com.op.gg.ogj.game.model.entity.QGame.game;
import static com.op.gg.ogj.gameInfo.model.entity.QGameInfo.gameInfo;

public class GameDslRepositoryImpl implements GameDslRepository{

    private final JPAQueryFactory jpaQueryFactory;

    public GameDslRepositoryImpl(EntityManager em){
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Game findGameByGameId(Long id) {
        return jpaQueryFactory.select(game)
                .from(game)
                .join(game.gameInfo, gameInfo).fetchJoin()
                .where(game.gameId.eq(id))
                .fetchOne();
    }

    @Override
    public Game findGameGameInfoByGameId(Long id) {
        return jpaQueryFactory.select(game)
                .from(game)
                .join(game.gameInfo, gameInfo).fetchJoin()
                .where(game.gameId.eq(id))
                .fetchOne();
    }

    @Override
    public Page<GameResponse> findPageGameByGameParam(GameSearch gameSearch) {
        System.out.println(gameSearch.getStartDate().atStartOfDay());
        JPAQuery<GameResponse> query = jpaQueryFactory.select(new QGameResponse(game.gameId, game.gameNm, game.price, game.brand, game.deviceType, game.gameType, game.gameInfo.gameInfo1, game.gameInfo.gameInfo2))
                .from(game)
                .join(game.gameInfo, gameInfo)
                .where(whereGameNm(gameSearch.getGameNm()), whereGameType(gameSearch.getGameType())
                , whereDeviceType(gameSearch.getDeviceType()), wherePriceRange(gameSearch.getStartPrice(), gameSearch.getEndPrice())
                , whereBrand(gameSearch.getBrand()), whereCreateDateRange(gameSearch.getStartDate().atStartOfDay(), gameSearch.getEndDate().atStartOfDay()))
                .offset(gameSearch.getPageable().getOffset())
                .limit(gameSearch.getPageable().getPageSize());

        if(gameSearch.getPageable().getSort() != null) selectOrder(gameSearch.getPageable().getSort(), query);

        QueryResults<GameResponse> result = query.fetchResults();

        return new PageImpl<>(result.getResults(), gameSearch.getPageable(), result.getTotal());
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

    private BooleanExpression whereGameNm(String gameNm){
        return StringUtils.hasText(gameNm)?game.gameNm.eq(gameNm):null;
    }

    private BooleanExpression whereGameType(GameFactoryMethod gameType){
        return gameType != null?game.gameType.eq(gameType):null;
    }

    private BooleanExpression whereDeviceType(DeviceType deviceType){
        return deviceType != null?game.deviceType.eq(deviceType):null;
    }

    private BooleanExpression wherePriceRange(Integer startPrice, Integer endPrice){
        return startPrice != null?game.price.between(startPrice, endPrice):null;
    }

    private BooleanExpression whereBrand(String brand){
        return StringUtils.hasText(brand)?game.brand.eq(brand):null;
    }

    private BooleanExpression whereCreateDateRange(LocalDateTime startDate, LocalDateTime endDate){
        return startDate != null?game.createDt.between(startDate, endDate):null;
    }
}
