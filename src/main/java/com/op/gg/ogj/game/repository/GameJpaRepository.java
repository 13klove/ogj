package com.op.gg.ogj.game.repository;

import com.op.gg.ogj.game.model.Game;
import com.op.gg.ogj.game.repository.queryDsl.GameDslRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameJpaRepository extends JpaRepository<Game, Long>, GameDslRepository {



}
