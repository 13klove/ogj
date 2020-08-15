package com.op.gg.ogj.game.repository;

import com.op.gg.ogj.game.model.entity.Game;
import com.op.gg.ogj.game.repository.queryDsl.GameDslRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GameJpaRepository extends JpaRepository<Game, Long>, GameDslRepository {

    Optional<Game> findGameByGameNm(String gameNm);

    Optional<Game> findGameByGameNmAndGameIdIsNot(String gameNm, Long gameId);

}
