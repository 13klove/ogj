package com.op.gg.ogj.game.valied;

import com.op.gg.ogj.game.model.GameParam;
import com.op.gg.ogj.game.repository.GameJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@RequiredArgsConstructor
public class GameValidComponent implements ConstraintValidator<GameValid, GameParam> {

    public final GameJpaRepository gameJpaRepository;

    @Override
    public void initialize(GameValid gameValid) {
    }

    @Override
    public boolean isValid(GameParam gameParam, ConstraintValidatorContext constraintValidatorContext) {

        if(!StringUtils.hasText(gameParam.getGameInfo1())) return valid(constraintValidatorContext, "게임 정보는 필수로 1개 이상 입력해야 합니다.");
        if(!StringUtils.hasText(gameParam.getGameNm())) return valid(constraintValidatorContext, "게임 이름은 필수 입니다.");
        if(gameParam.getGameType() == null) return valid(constraintValidatorContext, "게임타입은 필수 입니다.");
        if(gameParam.getPrice() == null) return valid(constraintValidatorContext, "게임 가격은 필수 입니다.");
        if(!StringUtils.hasText(gameParam.getBrand())) return valid(constraintValidatorContext, "게임 브렌드는 필수 입니다.");
        if(gameParam.getDeviceType() == null) return valid(constraintValidatorContext, "디바이스 타입은 필수 입니다.");

        return true;
    }

    public Boolean valid(ConstraintValidatorContext constraintValidatorContext, String msg){
        constraintValidatorContext.disableDefaultConstraintViolation();
        constraintValidatorContext.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
        return false;
    }

}
