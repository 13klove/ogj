package com.op.gg.ogj.map.repository.queryDsl.entity;

import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;

public class MapDslRepositoryImpl implements MapDslRepository{

    private final JPAQueryFactory jpaQueryFactory;

    public MapDslRepositoryImpl(EntityManager em){
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

}
