package com.op.gg.ogj.skin.repository;

import com.op.gg.ogj.skin.model.entity.Skin;
import com.op.gg.ogj.skin.repository.queryDsl.dto.SkinDtoDslRepository;
import com.op.gg.ogj.skin.repository.queryDsl.entity.SkinDslRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkinJpaRepository extends JpaRepository<Skin, Long>, SkinDslRepository, SkinDtoDslRepository {

}
