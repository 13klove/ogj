package com.op.gg.ogj.game.model;

import com.op.gg.ogj.game.valied.GameValid;
import com.op.gg.ogj.config.exception.domain.markup.Reg;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@GameValid(groups = {Reg.class})
public class GameParam {

    private Long gameId;

    private String gameNm;

    private Integer price;

    private String brand;

    private DeviceType deviceType;

    private Boolean actYn;

    private GameFactoryMethod gameType;

    //fps
    private Boolean storyYn;

    //aos
    private Boolean usemapYn;

    //prg
    private Boolean autoPlayYn;

    //rts
    private Boolean licenseYn;

    private String gameInfo1;

    private String gameInfo2;

}
