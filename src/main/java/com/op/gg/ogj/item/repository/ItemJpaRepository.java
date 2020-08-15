package com.op.gg.ogj.item.repository;

import com.op.gg.ogj.item.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemJpaRepository extends JpaRepository<Item, Long> {
}
