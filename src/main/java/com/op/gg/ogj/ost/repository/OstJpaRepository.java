package com.op.gg.ogj.ost.repository;

import com.op.gg.ogj.ost.model.entity.Ost;
import com.op.gg.ogj.ost.repository.queryDsl.dto.OstDtoDslRepository;
import com.op.gg.ogj.ost.repository.queryDsl.entity.OstDslRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OstJpaRepository extends JpaRepository<Ost, Long>, OstDtoDslRepository, OstDslRepository {

    List<Ost> findOstByOstIdIn(List<Long> ostsId);

    Ost findOstByGameGameIdAndOstNm(Long gameId, String ostNm);

    Ost findOstByOstIdIsNotAndOstNm(Long ostId, String ostNm);

    Optional<Ost> findOstByGameGameIdAndOstId(Long gameId, Long ostId);

}
