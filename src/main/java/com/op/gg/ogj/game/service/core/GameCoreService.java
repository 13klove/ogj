package com.op.gg.ogj.game.service.core;

import com.op.gg.ogj.character.model.entity.Character;
import com.op.gg.ogj.character.repository.CharacterJpaRepository;
import com.op.gg.ogj.game.model.dto.GameParam;
import com.op.gg.ogj.game.model.dto.GameResponse;
import com.op.gg.ogj.game.model.dto.GameSearch;
import com.op.gg.ogj.game.model.entity.Game;
import com.op.gg.ogj.game.repository.GameJpaRepository;
import com.op.gg.ogj.gameInfo.model.entity.GameInfo;
import com.op.gg.ogj.gameInfo.repository.GameInfoJpaRepository;
import com.op.gg.ogj.item.repository.ItemJpaRepository;
import com.op.gg.ogj.itemSpec.model.ItemSpec;
import com.op.gg.ogj.itemSpec.repository.ItemSpecJpaRepository;
import com.op.gg.ogj.map.repository.MapJpaRepository;
import com.op.gg.ogj.ost.repository.OstJpaRepository;
import com.op.gg.ogj.skill.repository.SkillJpaRepository;
import com.op.gg.ogj.skin.repository.SkinJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GameCoreService {

    private final GameJpaRepository gameJpaRepository;
    private final GameInfoJpaRepository gameInfoJpaRepository;
    private final OstJpaRepository ostJpaRepository;
    private final MapJpaRepository mapJpaRepository;
    private final CharacterJpaRepository characterJpaRepository;
    private final ItemJpaRepository itemJpaRepository;
    private final SkinJpaRepository skinJpaRepository;
    private final SkillJpaRepository skillJpaRepository;
    private final ItemSpecJpaRepository itemSpecJpaRepository;

    @Transactional
    public Long createGame(GameParam gameParam){
        Game game = Game.createGame(gameParam.getGameNm(), gameParam.getPrice(), gameParam.getBrand(), gameParam.getDeviceType(), gameParam.getGameType(), gameParam.getUsemapYn(), gameParam.getStoryYn(), gameParam.getAutoPlayYn(), gameParam.getLicenseYn());
        GameInfo gameInfo = GameInfo.createGameInfo(gameParam.getGameInfo1(), gameParam.getGameInfo2());
        game.gameInfoChange(gameInfo);
        gameJpaRepository.save(game);

        return game.getGameId();
    }

    @Transactional
    public Long updateGame(GameParam gameParam){
        Game game = gameJpaRepository.findGameGameInfoByGameId(gameParam.getGameId());
        game.updateGame(gameParam.getGameNm(), gameParam.getPrice(), gameParam.getBrand(), gameParam.getDeviceType(), gameParam.getGameType(), gameParam.getUsemapYn(), gameParam.getStoryYn(), gameParam.getAutoPlayYn(), gameParam.getLicenseYn());
        game.getGameInfo().gameInfoUpdate(gameParam.getGameInfo1(), gameParam.getGameInfo2());

        return game.getGameId();
    }

    public Page<GameResponse> pageGame(GameSearch gameSearch, Pageable pageable){ return gameJpaRepository.findPageGameByGameParam(gameSearch, pageable); }

    public GameResponse detailGame(GameSearch gameSearch){
        return gameJpaRepository.findGameByGameId(gameSearch);
    }

    @Transactional
    public void delGame(GameParam gameParam) {
        Game game = gameJpaRepository.findById(gameParam.getGameId()).get();
        List<Long> gameIds =  Arrays.asList(game.getGameInfo().getGameInfoId());
        gameJpaRepository.updateGameRelActYn(gameParam.getGameIds());
        gameInfoJpaRepository.updateGameInfoActYn(gameIds);
        characterJpaRepository.updateCharacterActYn(gameIds);
        List<Long> characterIds = characterJpaRepository.findCharacterIdsByGameIds(gameIds);
        //characterIds가 null 이면 어떻게 되는거지?
        itemJpaRepository.updateItemActYn(characterIds);
        List<Long> itemSpecIds = itemJpaRepository.findItemSpecIdsByCharacterIds(characterIds);
        itemSpecJpaRepository.updateItemSpecByItemSpecIds(itemSpecIds);

    }
}
