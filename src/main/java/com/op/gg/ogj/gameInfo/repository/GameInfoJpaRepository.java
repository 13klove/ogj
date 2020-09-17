package com.op.gg.ogj.gameInfo.repository;

import com.op.gg.ogj.gameInfo.model.entity.GameInfo;
import com.op.gg.ogj.gameInfo.repository.queryDsl.entity.GameInfoDslRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameInfoJpaRepository extends JpaRepository<GameInfo, Long>, GameInfoDslRepository {
}
