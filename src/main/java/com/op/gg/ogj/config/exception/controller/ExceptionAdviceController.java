package com.op.gg.ogj.config.exception.controller;

import com.op.gg.ogj.config.exception.domain.ErrorMessage;
import com.op.gg.ogj.config.exception.domain.ParamValidException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionAdviceController {

    @ResponseBody
    @ExceptionHandler(ParamValidException.class)
    public ErrorMessage handlerConflict(RuntimeException ex){
        return ErrorMessage.builder().status(HttpStatus.LOCKED.toString()).msg(ex.getMessage()).build();
    }

}
