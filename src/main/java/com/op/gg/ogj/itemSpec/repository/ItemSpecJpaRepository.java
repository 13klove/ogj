package com.op.gg.ogj.itemSpec.repository;

import com.op.gg.ogj.itemSpec.model.ItemSpec;
import com.op.gg.ogj.itemSpec.repository.queryDsl.entity.ItemSpecDslRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemSpecJpaRepository extends JpaRepository<ItemSpec, Long>, ItemSpecDslRepository {
}
