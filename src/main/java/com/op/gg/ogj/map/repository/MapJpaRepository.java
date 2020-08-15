package com.op.gg.ogj.map.repository;

import com.op.gg.ogj.map.model.Map;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MapJpaRepository extends JpaRepository<Map, Long> {
}
