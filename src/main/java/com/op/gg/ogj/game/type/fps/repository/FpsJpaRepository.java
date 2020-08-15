package com.op.gg.ogj.game.type.fps.repository;

import com.op.gg.ogj.game.type.fps.model.Fps;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FpsJpaRepository extends JpaRepository<Fps, Long> {
}
