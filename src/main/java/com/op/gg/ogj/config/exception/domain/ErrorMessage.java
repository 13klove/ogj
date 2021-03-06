package com.op.gg.ogj.config.exception.domain;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

@Getter @Setter
@AllArgsConstructor
@Builder
public class ErrorMessage {

    private int code;
    private String message;
    private LocalDate timestamp;
    private Object data;

    public ErrorMessage() {
        this.code = HttpStatus.OK.value();
        this.message = HttpStatus.OK.getReasonPhrase();
        this.timestamp = LocalDate.now();
    }

    public ErrorMessage(HttpStatus httpStatus) {
        this.code = httpStatus.value();
        this.message = httpStatus.getReasonPhrase();
        this.timestamp = LocalDate.now();
    }

    public ErrorMessage(HttpStatus httpStatus, Object obj) {
        this.code = httpStatus.value();
        this.message = httpStatus.getReasonPhrase();
        this.timestamp = LocalDate.now();
        this.data = obj;
    }


}
