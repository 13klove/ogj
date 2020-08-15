package com.op.gg.ogj.config.exception.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ParamValidException extends RuntimeException{

    private static final long serialVersionUID = 1L;
    private String message;

    public ParamValidException(String message){
        this.message = message;
    }
}
