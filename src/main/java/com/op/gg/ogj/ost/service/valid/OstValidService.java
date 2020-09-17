package com.op.gg.ogj.ost.service.valid;

import com.op.gg.ogj.game.valied.GameValid;
import com.op.gg.ogj.map.model.entity.Map;
import com.op.gg.ogj.map.repository.MapJpaRepository;
import com.op.gg.ogj.map.valid.MapValid;
import com.op.gg.ogj.ost.model.dto.OstParam;
import com.op.gg.ogj.ost.model.dto.OstSearch;
import com.op.gg.ogj.ost.model.entity.Ost;
import com.op.gg.ogj.ost.repository.OstJpaRepository;
import com.op.gg.ogj.ost.valid.OstValid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OstValidService {

    private final OstJpaRepository ostJpaRepository;
    private final MapJpaRepository mapJpaRepository;

    public void createOstValid(OstParam ostParam){
        createUpdate(ostParam);
        Ost ost = ostJpaRepository.findOstByGameGameIdAndOstNm(ostParam.getGameId(), ostParam.getOstNm());
        OstValid.OST_OST_NM_EXIT.validLogic(ost);
    }

    public void updateOstValid(OstParam ostParam){
        createUpdate(ostParam);
        OstValid.OST_OST_ID_LOCK.validLogic(ostParam.getOstId());
        Ost ost = ostJpaRepository.findOstByOstIdIsNotAndOstNm(ostParam.getOstId(), ostParam.getOstNm());
        OstValid.OST_OST_NM_EXIT.validLogic(ost);
    }

    public void pageOstValid(OstSearch ostSearch){ GameValid.GAME_ID_LOCK.validLogic(ostSearch.getGameId()); }

    public void detailOstValid(OstSearch ostSearch){ OstValid.OST_OST_ID_LOCK.validLogic(ostSearch.getOstId()); }

    public void delOst(OstParam ostParam){ OstValid.OST_OST_ID_LOCK.validLogic(ostParam.getOstId()); }

    public void delOsts(OstParam ostParam){ OstValid.OST_OST_IDS_LOCK.validLogic(ostParam.getOstsId()); }

    private void createUpdate(OstParam ostParam) {
        GameValid.GAME_ID_LOCK.validLogic(ostParam.getGameId());
        OstValid.OST_OST_NM_LOCK.validLogic(ostParam.getOstNm());

        if(ostParam.getMapId() != null){
            Map map = mapJpaRepository.findMapByGameIdMapId(ostParam.getGameId(), ostParam.getMapId());
            MapValid.MAP_MAP_NOT_EXIT.validLogic(map);
        }
    }

}
