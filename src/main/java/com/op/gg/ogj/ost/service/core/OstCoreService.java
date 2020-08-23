package com.op.gg.ogj.ost.service.core;

import com.op.gg.ogj.game.model.entity.Game;
import com.op.gg.ogj.game.repository.GameJpaRepository;
import com.op.gg.ogj.map.model.entity.Map;
import com.op.gg.ogj.map.repository.MapJpaRepository;
import com.op.gg.ogj.ost.model.dto.OstParam;
import com.op.gg.ogj.ost.model.dto.OstResponse;
import com.op.gg.ogj.ost.model.dto.OstSearch;
import com.op.gg.ogj.ost.model.entity.Ost;
import com.op.gg.ogj.ost.repository.OstJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OstCoreService {

    private final OstJpaRepository ostJpaRepository;
    private final GameJpaRepository gameJpaRepository;
    private final MapJpaRepository mapJpaRepository;

    @Transactional
    public Long createOst(OstParam ostParam){
        Ost ost = Ost.createOst(ostParam.getOstNm());
        Game game = gameJpaRepository.findById(ostParam.getGameId()).get();
        ost.smGmaeChange(game);
        if(ostParam.getMapId() != null){
            Map map = mapJpaRepository.findById(ostParam.getMapId()).get();
            ost.smMapChange(map);
        }
        return ostJpaRepository.save(ost).getOstId();
    }

    @Transactional
    public Long updateOst(OstParam ostParam){
        Ost ost = ostJpaRepository.findOstGameMapByOstId(ostParam.getOstId());
        ost.updateOst(ostParam.getOstNm());
        if(ostParam.getMapId() != null){
            Map map = mapJpaRepository.findMapByGameIdMapId(ostParam.getGameId(), ostParam.getMapId());
            ost.smMapChange(map);
        }else{
            ost.delMap();
        }

        return ost.getOstId();
    }

    public Page<OstResponse> pageOst(OstSearch ostSearch, Pageable pageable){
        return ostJpaRepository.findPageOst(ostSearch, pageable);
    }

    public OstResponse detailOst(OstSearch ostSearch){
        return ostJpaRepository.findDetailOst(ostSearch);
    }

    @Transactional
    public void delOst(OstParam ostParam){
        if(ostParam.getOstsId() != null && !ostParam.getOstsId().isEmpty()){
            List<Ost> osts = ostJpaRepository.findOstByOstIdIn(ostParam.getOstsId());
            osts.forEach(a->a.delOst());
        }else{
            Ost ost = ostJpaRepository.findById(ostParam.getOstId()).get();
            ost.delOst();
        }
    }

}
