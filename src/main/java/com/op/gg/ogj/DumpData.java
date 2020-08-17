package com.op.gg.ogj;

import com.google.common.collect.Lists;
import com.op.gg.ogj.game.model.DeviceType;
import com.op.gg.ogj.game.model.GameFactoryMethod;
import com.op.gg.ogj.game.model.dto.GameParam;
import com.op.gg.ogj.game.service.GameService;
import com.op.gg.ogj.map.model.dto.MapParam;
import com.op.gg.ogj.map.service.MapService;
import lombok.RequiredArgsConstructor;

import java.util.List;


@RequiredArgsConstructor
public class DumpData {

    private final GameService gameService;
    private final MapService mapService;

    public void mapData(){
        List<MapParam> list = Lists.newArrayList();

        //given
        MapParam mp1 = MapParam.builder().gameId(7l).mapNm("헌터").build();
        list.add(mp1);
        MapParam mp2 = MapParam.builder().gameId(3l).mapNm("헌터").build();
        list.add(mp2);
        MapParam mp3 = MapParam.builder().gameId(7l).mapNm("실피드").build();
        list.add(mp3);
        MapParam mp4 = MapParam.builder().gameId(7l).mapNm("로스트템플").build();
        list.add(mp4);
        MapParam mp5 = MapParam.builder().gameId(7l).mapNm("매치포인트").build();
        list.add(mp5);
        MapParam mp6 = MapParam.builder().gameId(3l).mapNm("부산역").build();
        list.add(mp6);


        MapParam mp7 = MapParam.builder().gameId(1l).mapNm("엑트1").build();
        list.add(mp7);
        MapParam mp8 = MapParam.builder().gameId(5l).mapNm("LA시티").build();
        list.add(mp8);
        MapParam mp9 = MapParam.builder().gameId(9l).mapNm("커닝시티").build();
        list.add(mp9);
        MapParam mp10 = MapParam.builder().gameId(11l).mapNm("칼바람").build();
        list.add(mp10);
        MapParam mp11 = MapParam.builder().gameId(13l).mapNm("투혼").build();
        list.add(mp11);

        MapParam mp12 = MapParam.builder().gameId(7l).mapNm("삭제1").build();
        list.add(mp12);
        MapParam mp13 = MapParam.builder().gameId(7l).mapNm("삭제2").build();
        list.add(mp13);
        MapParam mp14 = MapParam.builder().gameId(7l).mapNm("삭제3").build();
        list.add(mp14);


        list.forEach(a->mapService.createMap(a));
        //when
        //Long mapId = mapService.createMap(mp);
    }

    public void gameGameInfoData(){
        List<GameParam> list = Lists.newArrayList();

        GameParam gameParam1 = GameParam.builder()
                .gameNm("오버워치")
                .price(3300)
                .brand("븦리자드")
                .deviceType(DeviceType.PC)
                .gameType(GameFactoryMethod.FPS)
                .storyYn(true)
                .gameInfo1("팀전 fps")
                .build();

        GameParam gameParam2 = GameParam.builder()
                .gameNm("스타")
                .price(1200)
                .brand("븦리자드")
                .deviceType(DeviceType.PC)
                .gameType(GameFactoryMethod.RTS)
                .licenseYn(true)
                .gameInfo1("고전 게임")
                .gameInfo2("아프리카tv 대회")
                .build();

        GameParam gameParam3 = GameParam.builder()
                .gameNm("서든어택")
                .price(0)
                .brand("넥슨")
                .deviceType(DeviceType.PC)
                .gameType(GameFactoryMethod.FPS)
                .storyYn(false)
                .gameInfo1("넥슨 fps")
                .build();

        GameParam gameParam4 = GameParam.builder()
                .gameNm("리그오브레전드")
                .price(0)
                .brand("라이엇")
                .deviceType(DeviceType.PC)
                .gameType(GameFactoryMethod.AOS)
                .usemapYn(true)
                .gameInfo1("멀티 플레이 온라인 아레나")
                .build();

        GameParam gameParam5 = GameParam.builder()
                .gameNm("디아블로")
                .price(12000)
                .brand("블리자드")
                .deviceType(DeviceType.PC)
                .gameType(GameFactoryMethod.RPG)
                .autoPlayYn(false)
                .gameInfo1("악마를 사냥한다. 디아블로")
                .build();

        GameParam gameParam6 = GameParam.builder()
                .gameNm("바람의나라")
                .price(0)
                .brand("넥슨")
                .deviceType(DeviceType.MOBILE)
                .gameType(GameFactoryMethod.RPG)
                .autoPlayYn(true)
                .gameInfo1("pc의 향수를 느낄수 있다.")
                .build();

        GameParam gameParam7 = GameParam.builder()
                .gameNm("메이플스토리")
                .price(0)
                .brand("넥슨")
                .deviceType(DeviceType.MOBILE)
                .gameType(GameFactoryMethod.RPG)
                .autoPlayYn(true)
                .gameInfo1("pc의 향수를 느낄수 있다.")
                .build();

        list.add(gameParam1);
        list.add(gameParam2);
        list.add(gameParam3);
        list.add(gameParam4);
        list.add(gameParam5);
        list.add(gameParam6);
        list.add(gameParam7);

        list.forEach(a->gameService.createGame(a));
    }

}
