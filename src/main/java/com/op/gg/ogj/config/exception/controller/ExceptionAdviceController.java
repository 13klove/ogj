package com.op.gg.ogj.config.exception.controller;

import com.op.gg.ogj.config.exception.domain.ErrorMessage;
import com.op.gg.ogj.config.exception.domain.ParamValidException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDate;

@ControllerAdvice
public class ExceptionAdviceController {

    @ResponseBody
    @ResponseStatus(HttpStatus.LOCKED)
    @ExceptionHandler(ParamValidException.class)
    public ErrorMessage handlerConflict(RuntimeException ex){
        return ErrorMessage.builder().code(HttpStatus.LOCKED.value()).message(HttpStatus.LOCKED.getReasonPhrase())
                .timestamp(LocalDate.now()).data(ex.getMessage()).build();
    }

}
