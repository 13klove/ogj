package com.op.gg.ogj.gameInfo.repository;

import com.op.gg.ogj.gameInfo.model.GameInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameInfoJpaRepository extends JpaRepository<GameInfo, Long> {
}
