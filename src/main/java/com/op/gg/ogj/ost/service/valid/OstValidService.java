package com.op.gg.ogj.ost.service.valid;

import com.op.gg.ogj.config.exception.domain.ParamValidException;
import com.op.gg.ogj.map.model.entity.Map;
import com.op.gg.ogj.map.repository.MapJpaRepository;
import com.op.gg.ogj.ost.model.dto.OstParam;
import com.op.gg.ogj.ost.model.dto.OstSearch;
import com.op.gg.ogj.ost.model.entity.Ost;
import com.op.gg.ogj.ost.repository.OstJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OstValidService {

    private final OstJpaRepository ostJpaRepository;
    private final MapJpaRepository mapJpaRepository;

    public void createOstValid(OstParam ostParam){
        if(!StringUtils.hasText(ostParam.getOstNm())) throw new ParamValidException("ost 이름은 필수 입니다.");
        if(ostParam.getGameId() == null) throw new ParamValidException("gameId가 없습니다.");
        Ost ost = ostJpaRepository.findOstByGameGameIdAndOstNm(ostParam.getGameId(), ostParam.getOstNm());
        if(ost != null)  throw new ParamValidException("이미 등록된 ost 입니다.");
        if(ostParam.getMapId() != null){
            Map map = mapJpaRepository.findMapByGameIdMapId(ostParam.getGameId(), ostParam.getMapId());
            if(map == null) throw new ParamValidException("등록하려는 맵은 없습니다.");
        }
    }

    public void updateOstValid(OstParam ostParam){
        if(!StringUtils.hasText(ostParam.getOstNm())) throw new ParamValidException("ost 이름은 필수 입니다.");
        if(ostParam.getOstId() == null) throw new ParamValidException("ostId가 없습니다.");
        if(ostParam.getGameId() == null) throw new ParamValidException("gameId가 없습니다.");
        Optional<Ost> ostId = ostJpaRepository.findOstByGameGameIdAndOstId(ostParam.getGameId(), ostParam.getOstId());
        ostId.orElseThrow(() -> new ParamValidException("해당 ostId는 존재하지 않습니다."));
        Ost ost = ostJpaRepository.findOstByOstIdIsNotAndOstNm(ostParam.getOstId(), ostParam.getOstNm());
        if(ost != null) throw new ParamValidException("이미 등록된 ost 입니다.");
        if(ostParam.getMapId() != null){
            Map map = mapJpaRepository.findMapByGameIdMapId(ostParam.getGameId(), ostParam.getMapId());
            if(map == null) throw new ParamValidException("등록하려는 맵은 없습니다.");
        }
    }

    public void pageOstValid(OstSearch ostSearch){
        if(ostSearch.getGameId() == null) throw new ParamValidException("gameId가 없습니다.");
    }

    public void detailOstValid(OstSearch ostSearch){
        if(ostSearch.getOstId() == null) throw new ParamValidException("ostId가 없습니다.");
    }

    public void delOst(OstParam ostParam){
        if(ostParam.getOstId() == null && (ostParam.getOstsId() == null || ostParam.getOstsId().isEmpty())) throw new ParamValidException("ostId가 없습니다.");
    }

}
